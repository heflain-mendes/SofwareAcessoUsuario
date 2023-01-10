/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.view.OpcaoAcessoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendes
 */
public class OpcoesAcessoPresenter {

    private OpcaoAcessoView view;

    public OpcoesAcessoPresenter() {
        view = new OpcaoAcessoView();

        if (!UsuariosDAOService.getInstance().possuiCadastrosDeUsuario()) {
            view.getBtnEntra().setVisible(false);
            view.getLblLogin().setVisible(false);
        } else {
            view.getBtnEntra().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logar();
                }
            });
        }

        view.getBtnRegistra().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void logar() {
        view.dispose();
        new LoginPresenter();
    }

    private void cadastrar() {
        view.dispose();
        new RegistrarUsuarioPresenter();
    }
}
