/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces;

import com.ufes.sofwareacessousuario.model.User;

/**
 *
 * @author Heflain
 */
public interface IUserDAO {

    public long getQtdUserRegistered() throws Exception;

    public User login(String name, String password) throws Exception;

    public void registered(String name, String password) throws Exception;
    
    public void updatePassword(String password) throws Exception;
    
    public boolean nomeEmUso(String nome) throws Exception;
    
    public void autorizarUsuario(long id) throws Exception;
    
    public void removeUser(long id) throws Exception;
    
    public User getUsuario(long id) throws Exception;
    
    public String getNameUsuario(long id) throws Exception;
}
