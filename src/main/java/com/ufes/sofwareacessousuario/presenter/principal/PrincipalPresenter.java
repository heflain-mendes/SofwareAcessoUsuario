/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.view.PrincipalView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendesz
 */
public class PrincipalPresenter {

    PrincipalView view;
    private PrincipalPresenterState state;

    public PrincipalPresenter() {
        view = new PrincipalView();

        view.getBtnAddUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        view.getBtnVerNotificacoes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNotification();
            }
        });

        view.getBtnListarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUser();
            }
        });

        view.getBtnAlterarSenha().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });

        view.getBtnConfig().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config();
            }
        });

        view.getBtnSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        view.getBtnQtdNotificacoes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNotification();
            }
        });

        state = new UsuarioDeslogado(this);

        view.setVisible(true);
    }

    private void changePassword() {
        state.configurarSenha();
    }

    private void viewNotification() {
        state.verNotificacoes();
    }

    private void addUser() {
        state.addUsuario();
    }

    private void listUser() {
        state.listarUsuario();
    }

    private void config() {
        state.abrirConfiguracoes();
    }

    private void logout() {
        state.deslogar();
    }

    void setState(PrincipalPresenterState state) {
        this.state = state;
    }
    
    public void addView(Component c){
        this.state.addComponente(c);
    }
    
    public void removerView(Component c){
        this.state.removerComponente(c);
    }
}
