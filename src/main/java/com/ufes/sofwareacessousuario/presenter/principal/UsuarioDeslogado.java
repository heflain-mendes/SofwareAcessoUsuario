/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.OpcoesAcessoPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.command.AddComponente;
import com.ufes.sofwareacessousuario.presenter.principal.command.RemoverComponente;
import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import java.awt.Component;

/**
 *
 * @author Heflain
 */
public class UsuarioDeslogado extends PrincipalPresenterState implements EventListerners{

    public UsuarioDeslogado(PrincipalPresenter Principalpresenter) {
        super(Principalpresenter);
        
        Principalpresenter.view.getBtnAdiministrador().setVisible(false);
        Principalpresenter.view.getBtnAdiministrador().setEnabled(false);
        
        Principalpresenter.view.getBtnUsuario().setVisible(false);
        Principalpresenter.view.getBtnUsuario().setEnabled(false);
        
        Principalpresenter.view.getPnlInferior().setVisible(false);
        
        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
        
        new OpcoesAcessoPresenter(Principalpresenter);
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
        if(UsuarioLogadoServiceProxy.USUARIO_LOGADO.equals(mensagem)){
            UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
            new CarregandoPainelInferior(presenter);
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
