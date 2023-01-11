/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import java.util.List;
import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Heflain
 */
class UsuarioSQLiteDAO implements IUsuarioDAO {

    private String caminho;

    UsuarioSQLiteDAO(String caminho) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "    id     INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                + "    nome   TEXT    NOT NULL,"
                + "    senha  TEXT    NOT NULL,"
                + "    tipo   INTEGER NOT NULL DEFAULT 0,"
                + "    estado INTEGER NOT NULL DEFAULT 0,"
                + "    excluido INTEGER NOT NULL DEFAULT 0"
                + ");";

        this.caminho = caminho;

        try (
                Connection conn = DriverManager.getConnection(caminho); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Não foi possivel criar a tabela usuarios");
        }
    }

    @Override
    public UsuarioRetorno fazerLogin(String name, String password) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE nome=? AND senha=? AND excluido = ?;";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3, 0);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new UsuarioRetorno(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getInt("estado"),
                            rs.getInt("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Não foi possivel acessar a tabela User");
        }
        return null;
    }

    @Override
    public void registrar(UsuarioRegistro user) throws Exception {

        String sql = "INSERT INTO usuarios(nome, senha, estado, tipo) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getNome());
            ps.setString(2, user.getSenha());
            ps.setInt(3, user.getEstado());
            ps.setInt(4, user.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(
                    "Não foi possivel salvar informações do usuário: "
                    + user.getNome()
                    + "na tabela User"
            );
        }
    }

    @Override
    public void atualizarSenha(UsuarioRetorno user, String senha) throws Exception {
        String sql = "UPDATE usuarios SET senha=? WHERE id=? AND excluido = ?;";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, senha);
            ps.setLong(2, user.getId());
            ps.setInt(3, 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Não foi possivel atualizar informações do usuario: "
                    + user.getNome()
                    + " na tabela usuarios");
        }
    }

    @Override
    public boolean nomeEmUso(String nome) throws Exception {
        String sql = "SELECT id FROM usuarios WHERE nome=?;";
        try (Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            return rs.next();
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
    public void autorizarUsuario(UsuarioRetorno user) throws Exception {
        String sql = "UPDATE usuarios SET estado=? WHERE id=? AND excluido = ?;";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, UsuarioRegistro.AUTORIZADO);
            ps.setLong(2, user.getId());
            ps.setInt(3, 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Não foi possivel autorizar informações do usuario: "
                    + user.getNome()
                    + " na tabela usuarios");
        }
    }

    @Override
    public void removerUsuario(UsuarioRetorno user) throws Exception {
        String sql = "UPDATE usuarios SET excluido = ? WHERE id = ?";
        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Não foi possivel deletar usuário: " + user.getNome() + " na tabela usuario");
        }
    }

    @Override
    public UsuarioRetorno getUltimoUsuarioAdd() throws Exception {
        String sql = "SELECT * FROM usuarios where id = (SELECT MAX(id) FROM usuarios);";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new UsuarioRetorno(
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
    public List<UsuarioRetorno> getUsuarios() throws Exception {
        String sql = "SELECT * FROM usuarios WHERE excluido = ?";
        List<UsuarioRetorno> usuarios = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 0);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuarioRetorno f = new UsuarioRetorno(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getInt("estado"),
                            rs.getInt("tipo")
                    );

                    usuarios.add(f);
                }
                return usuarios;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possivel obter todos usuarios na tabela usuarios");
        }
    }

    @Override
    public String getNome(long id) throws Exception {
        String sql = "SELECT nome FROM usuarios WHERE id=?";

        try (Connection conn = DriverManager.getConnection(caminho)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Não foi possivel acessar a tabela User");
        }

        return null;
    }

    @Override
    public boolean possuiCadastrosDeUsuario() throws Exception {
        String sql = "SELECT * FROM usuarios LIMIT 1;";

        try (
                Connection conn = DriverManager.getConnection(caminho); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possivel saber se existe usuários");
        }

        return false;
    }

}
