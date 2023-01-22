/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.presenter.principal.command.AddComponente;
import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import com.ufes.sofwareacessousuario.presenter.principal.command.AtualizarNumeroDeNotificacoes;
import com.ufes.sofwareacessousuario.presenter.principal.command.RemoverComponente;
import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import java.awt.Component;

/**
 *
 * @author Heflain
 */
public class CarregandoPainelInferior extends PrincipalPresenterState {

    public CarregandoPainelInferior(PrincipalPresenter presenter) {
        super(presenter);

        presenter.view.getLblNomeUsuario().setText(UsuarioLogadoServiceProxy.getInstance().getNome()
        );

        UsuarioLogadoServiceProxy usuario = UsuarioLogadoServiceProxy.getInstance();

        presenter.view.getLblTipoUsuario().setText(usuario.getType().toUpperCase());
        
        presenter.view.getPnlInferior().setVisible(true);
        presenter.view.getBtnQtdNotificacoes().setVisible(true);

        if (usuario.getType() == UsuarioRetorno.ADMINISTRADOR) {
            new AtualizarNumeroDeNotificacoes(presenter.view).executar();
            new AdministradorLogado(presenter);
        } else {
            if(usuario.getState().equals(UsuarioRetorno.DESAUTORIZADO)){
                presenter.view.getBtnQtdNotificacoes().setVisible(false);
                new UsuarioNaoAutorizado(presenter);
            }else{
                new AtualizarNumeroDeNotificacoes(presenter.view).executar();
                new UsuarioLogado(presenter);
            }
        }
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
    public void addComponente(Component c) {
        new AddComponente(presenter.view, c).executar();
    }

    @Override
    public void removerComponente(Component c) {
        new RemoverComponente(presenter.view, c).executar();
    }
}
