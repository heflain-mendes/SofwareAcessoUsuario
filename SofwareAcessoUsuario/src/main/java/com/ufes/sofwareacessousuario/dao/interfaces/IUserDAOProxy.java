/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces;

import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.dao.service.UserRetorno;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface IUserDAOProxy {
    
    public List<UserRetorno> getUsuarios() throws Exception;

    public VerificacoesRegistro registrar(UserRegistro user) throws Exception;
    
    public UserRetorno getUltimoUsuarioAdd() throws Exception;
    
    public List<String> atualizarSenha(UserRetorno user, String senha) throws Exception;
    
    public void autorizarUsuario(UserRetorno user) throws Exception;
    
    public void removerUsuario(UserRetorno user) throws Exception;
    
    public UserRetorno fazerLogin(String name, String password) throws Exception;
    
    public boolean nomeEmUso(String nome) throws Exception;
    
    public String getNome(long id) throws Exception;
    
    public boolean possuiCadastrosDeUsuario() throws Exception;
}
