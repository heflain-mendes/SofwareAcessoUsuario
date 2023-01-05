/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAO;
import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.dao.UserRetorno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
public class UserSQLiteDAO implements IUserDAO {

    public UserSQLiteDAO() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "    id     INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                + "    nome   TEXT    NOT NULL,"
                + "    senha  TEXT    NOT NULL,"
                + "    tipo   INTEGER NOT NULL DEFAULT 0,"
                + "    estado INTEGER NOT NULL DEFAULT 0,"
                + "    excluido INTEGER NOT NULL DEFAULT 0"
                + ");";

        try (Statement st = SQLiteConnection.getConexao().createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Não foi possivel criar a tabela usuarios");
        }
    }

//    @Override
//    public long getQtdUserRegistered() throws Exception {
//        String sql = "SELECT COUNT(id) FROM usuarios;";
//
//        try (
//                Connection conn = SQLiteConnection.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
//
//            rs.next();
//
//            return rs.getLong(1);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            new Exception("Não foi possivel obter quantidade de usuarios cadastrados");
//        }
//
//        return -1;
//    }
    @Override
    public UserRetorno login(String name, String password) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE nome=? AND senha=? AND excluido = ?;";

        try (Connection conn = SQLiteConnection.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3, 0);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new UserRetorno(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getInt("estado"),
                        rs.getInt("tipo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Não foi possivel acessar a tabela User");
        }

        return null;
    }

    @Override
    public UserRetorno registered(UserRegistro user) throws Exception {
        String sql = "INSERT INTO usuarios(nome, senha, estado, tipo) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = SQLiteConnection.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getState());
            ps.setInt(4, user.getType());
            ps.executeUpdate();

            return getUltimoUsuarioAdd();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(
                    "Não foi possivel salvar informações do usuário: "
                    + user.getName()
                    + "na tabela User"
            );
        }
    }

    @Override
    public void updatePassword(UserRetorno user, String senha) throws Exception {
        String sql = "UPDATE usuarios SET senha=? WHERE id=? AND excluido = ?;";

        try (
                Connection conn = SQLiteConnection.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, senha);
            ps.setLong(2, user.getId());
            ps.setInt(3, 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Não foi possivel atualizar informações do usuario: "
                    + user.getName()
                    + " na tabela usuarios");
        }

    }

    @Override
    public boolean nomeEmUso(String nome) throws Exception {
        String sql = "SELECT id FROM usuarios WHERE nome=?;";
        try (Connection conn = SQLiteConnection.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(
                    "Não foi possivel saber se o nome: "
                    + nome
                    + " já está em uso"
            );
        }
    }

    @Override
    public void autorizarUsuario(UserRetorno user) throws Exception {
        String sql = "UPDATE usuarios SET estado=? WHERE id=? AND excluido = ?;";

        try (
                Connection conn = SQLiteConnection.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, UserRegistro.AUTORIZED);
            ps.setLong(2, user.getId());
            ps.setInt(3, 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Não foi possivel autorizar informações do usuario: "
                    + user.getName()
                    + " na tabela usuarios");
        }
    }

    @Override
    public void removeUser(UserRetorno user) throws Exception {
        String sql = "UPDATE usuarios SET excluido = ? WHERE id = ?";
        try (Connection conn = SQLiteConnection.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Não foi possivel deletar usuário: " + user.getName() + " na tabela usuario");
        }
    }

    private UserRetorno getUltimoUsuarioAdd() throws Exception {
        String sql = "SELECT * FROM usuarios where id = (SELECT MAX(id) FROM usuarios);";

        try (Connection conn = SQLiteConnection.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new UserRetorno(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getInt("estado"),
                        rs.getInt("tipo")
                );
            }

        } catch (Exception e) {
            throw new Exception("Não foi possivel obter o usuário na tabela usuário");
        }

        return null;
    }

    @Override
    public List<UserRetorno> getUsers() throws Exception {
        String sql = "SELECT * FROM usuarios WHERE excluido = ?";
        List<UserRetorno> usuarios = new ArrayList<>();
        try (Connection conn = SQLiteConnection.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserRetorno f = new UserRetorno(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getInt("estado"),
                        rs.getInt("tipo")
                );
                usuarios.add(f);
            }
            return usuarios;
        } catch (Exception e) {
            throw new Exception("Não foi possivel obter todos usuarios na tabela usuarios");
        }
    }

    @Override
    public String getNome(long id) throws Exception {
        String sql = "SELECT nome FROM usuarios WHERE id=?";

        try (Connection conn = SQLiteConnection.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Não foi possivel acessar a tabela User");
        }

        return null;
    }
}
