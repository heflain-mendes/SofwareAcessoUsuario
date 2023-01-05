/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces;

import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.dao.UserRetorno;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface IUserDAO {
    
    public List<UserRetorno> getUsers() throws Exception;

    public UserRetorno registered(UserRegistro user) throws Exception;
    
    public void updatePassword(UserRetorno user, String senha) throws Exception;
    
    public void autorizarUsuario(UserRetorno user) throws Exception;
    
    public void removeUser(UserRetorno user) throws Exception;
    
    public UserRetorno login(String name, String password) throws Exception;
    
    public boolean nomeEmUso(String nome) throws Exception;
    
    public String getNome(long id) throws Exception;
}
