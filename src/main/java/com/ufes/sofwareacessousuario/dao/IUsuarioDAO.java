/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface IUsuarioDAO {
    
    public List<UsuarioRetorno> getUsuarios() throws Exception;

    public void registrar(UsuarioRegistro user) throws Exception;
    
    public UsuarioRetorno getUltimoUsuarioAdd() throws Exception;
    
    public void atualizarSenha(UsuarioRetorno user, String senha) throws Exception;
    
    public void autorizarUsuario(UsuarioRetorno user) throws Exception;
    
    public void removerUsuario(UsuarioRetorno user) throws Exception;
    
    public UsuarioRetorno fazerLogin(String name, String password) throws Exception;
    
    public boolean nomeEmUso(String nome) throws Exception;
    
    public String getNome(long id) throws Exception;
    
    public boolean possuiCadastrosDeUsuario() throws Exception;
}
