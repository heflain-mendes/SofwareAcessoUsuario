/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces.sqlite;

import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAO;
import com.ufes.sofwareacessousuario.model.User;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Heflain
 */
public class UserSQLiteDAO implements IUserDAO {

    public UserSQLiteDAO() throws Exception{
        String sql = "CREATE TABLE IF NOT EXISTS User ("
                + "    id     INTEGER CONSTRAINT User_Constraint PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,"
                + "    nome   TEXT    NOT NULL,"
                + "    senha  TEXT    NOT NULL,"
                + "    tipo   INTEGER NOT NULL,"
                + "    estado INTEGER NOT NULL"
                + ");";
        
        try ( Statement st = SQLiteConnection.getConexao().createStatement()) {
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Não foi possivel criar a tabela tipo_bonus");
        }
    }

    @Override
    public long getQtdUserRegistered() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User login(String name, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void registered(String name, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updatePassword(String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean nomeEmUso(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void autorizarUsuario(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUser(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getUsuario(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNameUsuario(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
