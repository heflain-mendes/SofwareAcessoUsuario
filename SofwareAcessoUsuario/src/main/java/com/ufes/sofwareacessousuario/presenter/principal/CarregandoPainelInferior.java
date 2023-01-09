/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.dao.service.UserRetorno;
import com.ufes.sofwareacessousuario.presenter.principal.command.AtualizarNumeroDeNotificacoes;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;

/**
 *
 * @author Heflain
 */
public class CarregandoPainelInferior extends PrincipalPresenterState {

    public CarregandoPainelInferior(PrincipalPresenter presenter) {
        super(presenter);

        presenter.view.getLblUserName().setText(UsuarioLogadoService.getInstance().getNome()
        );

        UsuarioLogadoService usuario = UsuarioLogadoService.getInstance();

        presenter.view.getLblUserType().setText(usuario.getType() == UserRetorno.ADMINISTERED ? "ADMINISTRADOR" : "USER"
        );
        
        presenter.view.getPnlBottom().setVisible(true);
        presenter.view.getBtnAmountNotifications().setVisible(true);

        if (usuario.getType() == UserRetorno.ADMINISTERED) {
            new AtualizarNumeroDeNotificacoes(presenter.view).executar();
            new AdministradorLogado(presenter);
        } else {
            if(usuario.getState().equals(UserRetorno.UNAUTORIZED)){
                presenter.view.getBtnAmountNotifications().setVisible(false);
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
}
