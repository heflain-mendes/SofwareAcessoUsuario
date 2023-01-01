/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.service.UserLoggedService;
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
public class UpdateUserPresenter {

    private RegisterUserView view;

    public UpdateUserPresenter() {
        view = new RegisterUserView();
        view.setTitle("Atualizar senha");

        view.getLblInvalidName().setVisible(false);
        view.getLblInvalidPassword().setVisible(false);
        view.getLblNomeUsuarioUso().setVisible(false);

        view.getTxtUserName().setText(UserLoggedService.getNome());
        view.getTxtUserName().setEnabled(false);

        view.getBtnRegistre().setText("Atualizar");
        view.getBtnRegistre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
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

    private void atualizar() {
        var senha = String.valueOf(view.getTxtPassword().getPassword());
        var confirmacaoSenha = String.valueOf(view.getTxtConfirmPassword().getPassword());

        boolean senhaConfere = senha.equals(confirmacaoSenha);
        boolean senhaValida = false;

        if (senhaConfere) {
            view.getLblInvalidPassword().setVisible(false);
            senhaValida = senhaValida(senha);
        } else {
            view.getLblInvalidPassword().setVisible(true);
        }

        if (senhaValida) {
            UserDAOService.updatePassword(senha);

            JOptionPane.showMessageDialog(
                    null,
                    "Senha alterada com sucesso",
                    "Senha alterada",
                    JOptionPane.INFORMATION_MESSAGE
            );

            view.dispose();
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

    private void fechar() {
        view.dispose();
    }
}
