/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.UserRetorno;
import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.dao.UsersDAOService;
import com.ufes.sofwareacessousuario.dao.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.view.SendNotificationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class SendNotificationPresenter {

    private UserRetorno userReceptor;
    private SendNotificationView view;

    public SendNotificationPresenter(long idUserReceptor) {
        this.userReceptor = UsersDAOService.getInstance().getUsuario(idUserReceptor);

        view = new SendNotificationView();

        view.getTxtAddressees().setText(userReceptor.getName());
        view.getTxtAddressees().setEnabled(false);

        view.getbtnSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        
        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void submit() {
        UsersDAOService.getInstance().enviarNoticacao(
                userReceptor,
                view.getTxtAssunto().getText(),
                view.getTxtMessage().getText()
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
