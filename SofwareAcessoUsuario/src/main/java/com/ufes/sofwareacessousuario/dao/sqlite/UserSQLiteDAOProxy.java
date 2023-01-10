/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.dao.service.UserRetorno;
import java.util.List;
import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAOProxy;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.validacaonome.ValidadorNome;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;

/**
 *
 * @author Heflain
 */
public class UserSQLiteDAOProxy implements IUserDAOProxy {

    private UserSQLiteReal real;

    public UserSQLiteDAOProxy(String caminho) throws Exception{
        real =  new UserSQLiteReal(caminho);
    }

    @Override
    public List<UserRetorno> getUsuarios() throws Exception {
        return real.getUsuarios();
    }

    @Override
    public void registrar(UserRegistro user) throws Exception {
        VerificacoesRegistro verificacoes = new VerificacoesRegistro(
                nomeEmUso(user.getName()),
                new ValidadorNome().validar(user.getName()),
                new VerificadorSenha().verificar(user.getPassword())
        );

        if (verificacoes.possuiRecusas()) {
            return;
        }
        real.registrar(user);
    }

    @Override
    public UserRetorno getUltimoUsuarioAdd() throws Exception {
        return real.getUltimoUsuarioAdd();
    }

    @Override
    public void atualizarSenha(UserRetorno user, String senha) throws Exception {
        List<String> validacoesSenha = new VerificadorSenha().verificar(senha);

        if (!validacoesSenha.isEmpty()) {
            return;
        }
        
        real.atualizarSenha(user, senha);
    }

    @Override
    public void autorizarUsuario(UserRetorno user) throws Exception {
        real.autorizarUsuario(user);
    }

    @Override
    public void removerUsuario(UserRetorno user) throws Exception {
        real.removerUsuario(user);
    }

    @Override
    public UserRetorno fazerLogin(String name, String password) throws Exception {
        return real.fazerLogin(name, password);
    }

    @Override
    public boolean nomeEmUso(String nome) throws Exception {
        return real.nomeEmUso(nome);
    }

    @Override
    public String getNome(long id) throws Exception {
        return real.getNome(id);
    }

    @Override
    public boolean possuiCadastrosDeUsuario() throws Exception {
        return real.possuiCadastrosDeUsuario();
    }

}