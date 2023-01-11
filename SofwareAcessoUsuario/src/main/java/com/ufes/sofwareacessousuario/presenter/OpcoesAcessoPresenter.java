/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.util.UsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.view.OpcaoAcessoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendes
 */
public class OpcoesAcessoPresenter {

    private OpcaoAcessoView view;
    private PrincipalPresenter principalPresenter;

    public OpcoesAcessoPresenter(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
        view = new OpcaoAcessoView();

        if (!UsuariosDAOServiceProxy.getInstance().possuiUsuariosCadastrados()) {
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

        principalPresenter.addView(view);
        view.setVisible(true);
    }

    private void logar() {
        view.dispose();
        principalPresenter.removerView(view);
        new LoginPresenter(principalPresenter);
    }

    private void cadastrar() {
        view.dispose();
        principalPresenter.removerView(view);
        new RegistrarUsuarioPresenter(principalPresenter);
    }
}
