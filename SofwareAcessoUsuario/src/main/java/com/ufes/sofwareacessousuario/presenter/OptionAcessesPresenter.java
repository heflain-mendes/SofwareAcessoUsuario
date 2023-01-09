/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.view.OptionAcessesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendes
 */
public class OptionAcessesPresenter {

    private OptionAcessesView view;

    public OptionAcessesPresenter() {
        view = new OptionAcessesView();

        if (!UsuariosDAOService.getInstance().possuiCadastrosDeUsuario()) {
            view.getBtnSingIn().setVisible(false);
            view.getLblLogin().setVisible(false);
        } else {
            view.getBtnSingIn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    singIn();
                }
            });
        }

        view.getBtnSingUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singUp();
            }
        });

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void singIn() {
        view.dispose();
        new LoginPresenter();
    }

    private void singUp() {
        view.dispose();
        new RegisterUserPresenter();
    }
}
