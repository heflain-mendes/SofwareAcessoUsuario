/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.service.LoggedUserService;
import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.service.UserDAOService;
import com.ufes.sofwareacessousuario.validacaosenha.ValidadorSenha;
import com.ufes.sofwareacessousuario.view.RegisterUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
        view.getTxtInvalidName().setVisible(false);
        
        view.getTxtInvalidName().setText(
                 view.getTxtInvalidName().getText() + " " + TAMNHO_MIN_NOME
        );

        view.getBtnRegistre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });

        PrincipalViewService.getPrincipalView().getPnlPrincipal().add(view);
        view.setVisible(true);
    }

    private void registrar() {
        String nome = this.view.getTxtUserName().getText();
        String senha = String.valueOf(this.view.getTxtPassword().getPassword());
        String confirmacaoSenha = String.valueOf(this.view.getTxtConfirmPassword().getPassword());

        boolean nomevalido = nome.length() >= TAMNHO_MIN_NOME;
        boolean senhaConfere = senha.equals(confirmacaoSenha);

        if (nomevalido) {
            view.getTxtInvalidName().setVisible(false);

        } else {
            this.view.getTxtInvalidName().setVisible(true);

        }

        if (senhaConfere) {
            view.getLblInvalidPassword().setVisible(false);

            if (senhaValida(senha)) {
                UserDAOService.registered(nome, senha);

                JOptionPane.showMessageDialog(
                        null,
                        "Cadastrado com sucesso",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                view.dispose();

                if (LoggedUserService.getUser() == null) {
                    new OptionAcessesPresenter();
                }
            } else {
                this.view.getTxtPassword().setText("");
                this.view.getTxtConfirmPassword().setText("");
            }
        } else {
            view.getLblInvalidPassword().setVisible(true);
        }
    }

    private boolean senhaValida(String senha) {
        List<String> recusas = new ValidadorSenha().verificar(senha);

        if (recusas.size() == 0) {
            return true;
        } else {
            StringBuilder listaRecusas = new StringBuilder();

            for (var r : recusas) {
                listaRecusas.append(r).append("\n");
            }

            JOptionPane.showMessageDialog(
                    null,
                    recusas.toString(),
                    "Problema com a senha",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return false;
        }
    }
}
