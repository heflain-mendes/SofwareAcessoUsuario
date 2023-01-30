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
                state.addUsuario();
            }
        });

        view.getBtnVerNotificacoes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.verNotificacoes();
            }
        });

        view.getBtnListarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.listarUsuario();
            }
        });

        view.getBtnAlterarSenha().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.configurarSenha();
            }
        });

        view.getBtnConfig().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.abrirConfiguracoes();
            }
        });

        view.getBtnSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.deslogar();
            }
        });

        view.getBtnQtdNotificacoes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.verNotificacoes();
            }
        });

        state = new UsuarioDeslogado(this);

        view.setVisible(true);
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
