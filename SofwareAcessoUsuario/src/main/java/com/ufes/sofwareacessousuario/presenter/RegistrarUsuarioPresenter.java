/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.util.UsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.view.RegistraUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class RegistrarUsuarioPresenter {

    private RegistraUsuarioView view;
    private PrincipalPresenter principalPresenter;

    public RegistrarUsuarioPresenter(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
        view = new RegistraUsuarioView();

        view.getLblInvalidPassword().setVisible(false);
        view.getLblInvalidName().setVisible(false);
        view.getLblNomeUsuarioUso().setVisible(false);

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

        principalPresenter.addView(view);
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

        VerificacoesRegistro verificao = UsuariosDAOServiceProxy.getInstance().RegistrarUsuario(nome, senha);

        if (verificao.possuiRecusas()) {
            if (verificao.nomeEstaEmUso()) {
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
        principalPresenter.removerView(view);
        if (!UsuarioLogadoServiceProxy.getInstance().isLogado()) {
            new OpcoesAcessoPresenter(principalPresenter);
        }
    }
}
