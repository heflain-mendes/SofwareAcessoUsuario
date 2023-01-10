/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import com.ufes.sofwareacessousuario.dao.service.UsuarioRetorno;
import java.util.List;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.validacaonome.ValidadorNome;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import com.ufes.sofwareacessousuario.dao.interfaces.IUsuarioDAOProxy;

/**
 *
 * @author Heflain
 */
public class UsuarioSQLiteDAOProxy implements IUsuarioDAOProxy {

    private UsuarioSQLiteReal real;

    public UsuarioSQLiteDAOProxy(String caminho) throws Exception{
        real =  new UsuarioSQLiteReal(caminho);
    }

    @Override
    public List<UsuarioRetorno> getUsuarios() throws Exception {
        return real.getUsuarios();
    }

    @Override
    public void registrar(UsuarioRegistro user) throws Exception {
        VerificacoesRegistro verificacoes = new VerificacoesRegistro(
                nomeEmUso(user.getNome()),
                new ValidadorNome().validar(user.getNome()),
                new VerificadorSenha().verificar(user.getSenha())
        );

        if (verificacoes.possuiRecusas()) {
            return;
        }
        real.registrar(user);
    }

    @Override
    public UsuarioRetorno getUltimoUsuarioAdd() throws Exception {
        return real.getUltimoUsuarioAdd();
    }

    @Override
    public void atualizarSenha(UsuarioRetorno user, String senha) throws Exception {
        List<String> validacoesSenha = new VerificadorSenha().verificar(senha);

        if (!validacoesSenha.isEmpty()) {
            return;
        }
        
        real.atualizarSenha(user, senha);
    }

    @Override
    public void autorizarUsuario(UsuarioRetorno user) throws Exception {
        real.autorizarUsuario(user);
    }

    @Override
    public void removerUsuario(UsuarioRetorno user) throws Exception {
        real.removerUsuario(user);
    }

    @Override
    public UsuarioRetorno fazerLogin(String name, String password) throws Exception {
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
