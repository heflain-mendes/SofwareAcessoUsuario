/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.view.LoginView;
import com.ufes.sofwareacessousuario.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class LoginPresenter implements EventListerners{

    private LoginView view;
    private PrincipalView principalView;
    private PrincipalPresenter principalPresenter;

    public LoginPresenter(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
        this.view = new LoginView();

        view.getLblNomeDeUsuarioOuSenhaInvalidos().setVisible(false);

        view.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        principalPresenter.addView(view);

        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
        
        view.setVisible(true);
    }

    private void login() {
        String name = this.view.getTxtNomeUsuario().getText();
        String password = String.valueOf(this.view.getTxtSenha().getPassword());

        try {
            if (!UsuarioLogadoServiceProxy.getInstance().login(name, password)) {
                view.getLblNomeDeUsuarioOuSenhaInvalidos().setVisible(true);
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void fechar() {
        UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
        view.dispose();
        principalPresenter.removerView(view);
        if (!UsuarioLogadoServiceProxy.getInstance().isLogado()) {
            new OpcoesAcessoPresenter(principalPresenter);
        }
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoServiceProxy.USUARIO_LOGADO)){
            principalPresenter.removerView(view);
            view.dispose();
            UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
        }
    }
}
