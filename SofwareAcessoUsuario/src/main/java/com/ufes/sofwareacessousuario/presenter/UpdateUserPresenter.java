/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
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

        view.getTxtUserName().setText(UsuarioLogadoService.getInstance().getNome());
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
        List<String> recusas;

        if (senhaConfere) {
            view.getLblInvalidPassword().setVisible(false);
            recusas = UsuarioLogadoService.getInstance().atualizarSenha(senha);

            if (recusas.isEmpty()) {
                UsuarioLogadoService.getInstance().atualizarSenha(senha);

                JOptionPane.showMessageDialog(
                        null,
                        "Senha alterada com sucesso",
                        "Senha alterada",
                        JOptionPane.INFORMATION_MESSAGE
                );

                view.dispose();
            }else{
                JOptionPane.showMessageDialog(
                        null,
                        String.join("\n", recusas),
                        "Problema com a senha",
                        JOptionPane.INFORMATION_MESSAGE
                );
                view.getTxtPassword().setText("");
                view.getTxtConfirmPassword().setText("");
            }
        } else {
            view.getLblInvalidPassword().setVisible(true);
        }
    }

    private void fechar() {
        view.dispose();
    }
}
