/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.command.ConfigurarSenha;
import com.ufes.sofwareacessousuario.presenter.principal.command.Deslogar;
import com.ufes.sofwareacessousuario.presenter.principal.command.AtualizarNumeroDeNotificacoes;
import com.ufes.sofwareacessousuario.presenter.principal.command.VerNotificacoes;

/**
 *
 * @author Heflain
 */
public class UsuarioLogado extends PrincipalPresenterState implements EventListerners{

    public UsuarioLogado(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnChangePassword().setEnabled(true);
        presenter.view.getBtnViewNotificaition().setEnabled(true);
        presenter.view.getBtnChangePassword().setVisible(true);
        presenter.view.getBtnViewNotificaition().setVisible(true);
        
        presenter.view.getBtnAdmin().setVisible(false);
        presenter.view.getBtnAdmin().setEnabled(false);
        
        presenter.view.getBtnUser().setVisible(true);
        presenter.view.getBtnUser().setEnabled(true);
        
        UsuarioLogadoService.getInstance().subcribe(this);
    }

    @Override
    public void configurarSenha() {
        new ConfigurarSenha().executar();
    }

    @Override
    public void verNotificacoes() {
        new VerNotificacoes().executar();
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
        UsuarioLogadoService.getInstance().unsubcribe(this);
    }
    
    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoService.MARCADO_LIDO)){
            new AtualizarNumeroDeNotificacoes(presenter.view).executar();
        }
    }
}
