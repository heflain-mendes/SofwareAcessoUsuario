/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.command.AddComponente;
import com.ufes.sofwareacessousuario.presenter.principal.command.AlterarSenha;
import com.ufes.sofwareacessousuario.presenter.principal.command.Deslogar;
import com.ufes.sofwareacessousuario.presenter.principal.command.AtualizarNumeroDeNotificacoes;
import com.ufes.sofwareacessousuario.presenter.principal.command.RemoverComponente;
import com.ufes.sofwareacessousuario.presenter.principal.command.VerNotificacoes;
import java.awt.Component;

/**
 *
 * @author Heflain
 */
public class UsuarioLogado extends PrincipalPresenterState implements EventListerners{

    public UsuarioLogado(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAlterarSenha().setEnabled(true);
        presenter.view.getBtnVerNotificacoes().setEnabled(true);
        presenter.view.getBtnAlterarSenha().setVisible(true);
        presenter.view.getBtnVerNotificacoes().setVisible(true);
        
        presenter.view.getBtnAdiministrador().setVisible(false);
        presenter.view.getBtnAdiministrador().setEnabled(false);
        
        presenter.view.getBtnUsuario().setVisible(true);
        presenter.view.getBtnUsuario().setEnabled(true);
        
        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
    }

    @Override
    public void configurarSenha() {
        new AlterarSenha(presenter).executar();
    }

    @Override
    public void verNotificacoes() {
        new VerNotificacoes(presenter).executar();
    }

    @Override
    public void addUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void abrirConfiguracoes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deslogar() {
        new Deslogar(presenter, presenter.view).executar();
        UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
    }
    
    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoServiceProxy.MARCADO_LIDO)){
            new AtualizarNumeroDeNotificacoes(presenter.view).executar();
        }
    }
    
    @Override
    public void addComponente(Component c) {
        new AddComponente(presenter.view, c).executar();
    }

    @Override
    public void removerComponente(Component c) {
        new RemoverComponente(presenter.view, c).executar();
    }
}
