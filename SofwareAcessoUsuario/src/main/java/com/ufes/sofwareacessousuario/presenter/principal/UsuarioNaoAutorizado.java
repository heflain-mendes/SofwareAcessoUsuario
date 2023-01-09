/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.presenter.principal.command.Deslogar;
import javax.swing.JOptionPane;

/**
 *
 * @author Heflain
 */
public class UsuarioNaoAutorizado extends PrincipalPresenterState{

    public UsuarioNaoAutorizado(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnChangePassword().setEnabled(false);
        presenter.view.getBtnViewNotificaition().setEnabled(false);
        presenter.view.getBtnChangePassword().setVisible(false);
        presenter.view.getBtnViewNotificaition().setVisible(false);
        
        presenter.view.getBtnUser().setVisible(true);
        presenter.view.getBtnUser().setEnabled(true);
        
        presenter.view.getPnlPrincipal().removeAll();
        
        JOptionPane.showMessageDialog(
                presenter.view.getPnlPrincipal(),
                "Usuário ainda não foi autorizado",
                "Agurde autorização",
                JOptionPane.INFORMATION_MESSAGE
        );
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
        new Deslogar(presenter, presenter.view).executar();
    }
    
}
