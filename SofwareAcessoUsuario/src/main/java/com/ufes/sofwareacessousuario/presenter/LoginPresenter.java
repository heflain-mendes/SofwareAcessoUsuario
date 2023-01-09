/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class LoginPresenter implements EventListerners{

    private LoginView view;

    public LoginPresenter() {
        this.view = new LoginView();

        view.getLblInvalidUserNameOrPassword().setVisible(false);

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

        PrincipalViewService.add(view);

        UsuarioLogadoService.getInstance().subcribe(this);
        
        view.setVisible(true);
    }

    private void login() {
        String name = this.view.getTxtUserName().getText();
        String password = String.valueOf(this.view.getTxtPassword().getPassword());

        try {
            if (!UsuarioLogadoService.getInstance().login(name, password)) {
                view.getLblInvalidUserNameOrPassword().setVisible(true);
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
        view.dispose();
        if (!UsuarioLogadoService.getInstance().userLogged()) {
            new OptionAcessesPresenter();
        }
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoService.USUARIO_LOGADO)){
            view.dispose();
            UsuarioLogadoService.getInstance().unsubcribe(this);
        }
    }
}
