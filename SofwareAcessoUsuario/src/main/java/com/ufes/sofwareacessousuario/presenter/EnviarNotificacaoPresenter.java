/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.service.UsuarioRetorno;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.view.EnviarNotificacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class EnviarNotificacaoPresenter {

    private UsuarioRetorno userReceptor;
    private EnviarNotificacaoView view;

    public EnviarNotificacaoPresenter(long idUserReceptor) {
        this.userReceptor = UsuariosDAOService.getInstance().getUsuario(idUserReceptor);

        view = new EnviarNotificacaoView();

        view.getTxtDestinatario().setText(userReceptor.getNome());
        view.getTxtDestinatario().setEnabled(false);

        view.getbtnEnviar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviar();
            }
        });
        
        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void enviar() {
        UsuariosDAOService.getInstance().enviarNoticacao(
                userReceptor,
                view.getTxtAssunto().getText(),
                view.getTxtMessagem().getText()
        );
        
        JOptionPane.showMessageDialog(
                null,
                "Eviado com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
        
        view.dispose();
    }
}
