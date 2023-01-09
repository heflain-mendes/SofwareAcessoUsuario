/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.OptionAcessesPresenter;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;

/**
 *
 * @author Heflain
 */
public class UsuarioDeslogado extends PrincipalPresenterState implements EventListerners{

    public UsuarioDeslogado(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAdmin().setVisible(false);
        presenter.view.getBtnAdmin().setEnabled(false);
        
        presenter.view.getBtnUser().setVisible(false);
        presenter.view.getBtnUser().setEnabled(false);
        
        presenter.view.getPnlBottom().setVisible(false);
        
        UsuarioLogadoService.getInstance().subcribe(this);
        
        new OptionAcessesPresenter();
    }

    @Override
    public void configurarSenha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void verNotificacoes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(String mensagem) {
        if(UsuarioLogadoService.USUARIO_LOGADO.equals(mensagem)){
            UsuarioLogadoService.getInstance().unsubcribe(this);
            new CarregandoPainelInferior(presenter);
        }
    }
}
