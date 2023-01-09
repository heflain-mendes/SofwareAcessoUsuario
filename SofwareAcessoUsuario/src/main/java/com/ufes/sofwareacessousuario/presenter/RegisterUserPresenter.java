/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.view.RegisterUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class RegisterUserPresenter {

    private static final int TAMNHO_MIN_NOME = 6;

    private RegisterUserView view;

    public RegisterUserPresenter() {
        view = new RegisterUserView();

        view.getLblInvalidPassword().setVisible(false);
        view.getLblInvalidName().setVisible(false);
        view.getLblNomeUsuarioUso().setVisible(false);

        view.getLblInvalidName().setText(
                view.getLblInvalidName().getText() + " " + TAMNHO_MIN_NOME
        );

        view.getBtnRegistre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });

        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void registrar() {
        String nome = this.view.getTxtUserName().getText();
        String senha = String.valueOf(this.view.getTxtPassword().getPassword());
        String confirmacaoSenha = String.valueOf(this.view.getTxtConfirmPassword().getPassword());
        boolean senhaConfere = senha.equals(confirmacaoSenha);

        if (!senhaConfere) {
            view.getLblInvalidPassword().setVisible(true);
            return;
        }
        view.getLblInvalidPassword().setVisible(false);

        VerificacoesRegistro verificao = 
                UsuariosDAOService.getInstance().registered(nome, senha);

        if (verificao.possuiRecusas()) {
            if (verificao.isNomeEmUso()) {
                view.getLblNomeUsuarioUso().setVisible(true);
            } else {
                view.getLblNomeUsuarioUso().setVisible(false);
            }

            if (verificao.getRecusasNome().isEmpty()) {
                view.getLblInvalidName().setText("");
                view.getLblInvalidName().setVisible(false);
            } else {
                String texto = "nome deve " + verificao.toStringRecusasNome();
                view.getLblInvalidName().setText(texto);
                view.getLblInvalidName().setVisible(true);
            }

            if (!verificao.getRecusasSenha().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        verificao.toStringRecusasSenha(),
                        "Problema com a senha",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Cadastrado com sucesso",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            fechar();
        }
    }

    public void fechar() {
        view.dispose();
        if (!UsuarioLogadoService.getInstance().userLogged()) {
            new OptionAcessesPresenter();
        }
    }
}
