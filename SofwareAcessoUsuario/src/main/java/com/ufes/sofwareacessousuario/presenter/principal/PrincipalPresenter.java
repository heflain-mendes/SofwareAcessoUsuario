/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.view.PrincipalView;
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
        PrincipalViewService.setPrincipalView(view);

        view.getBtnAddUser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        view.getBtnViewNotificaition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNotification();
            }
        });

        view.getBtnListUsers().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUser();
            }
        });

        view.getBtnChangePassword().addActionListener(new ActionListener() {
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

        view.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        view.getBtnAmountNotifications().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNotification();
            }
        });

        state = new UsuarioDeslogado(this);

        view.setVisible(true);
    }

    public void changePassword() {
        state.configurarSenha();
    }

    public void viewNotification() {
        state.verNotificacoes();
    }

    public void addUser() {
        state.addUsuario();
    }

    public void listUser() {
        state.listarUsuario();
    }

    public void config() {
        state.abrirConfiguracoes();
    }

    public void logout() {
        state.deslogar();
    }

    void setState(PrincipalPresenterState state) {
        this.state = state;
    }
}
