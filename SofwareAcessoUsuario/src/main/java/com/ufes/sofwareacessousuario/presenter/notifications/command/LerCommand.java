/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.presenter.notifications.NotificationTable;
import com.ufes.sofwareacessousuario.dao.service.NotificationRetorno;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.view.NotificationsView;

/**
 *
 * @author Heflain
 */
public class LerCommand implements NotificationsCommand {

    @Override
    public void executar(NotificationsView view, NotificationTable table) {
        int linha = view.getTblNotificacoes().getSelectedRow();
        if (linha == -1) {
            return;
        }

        view.getTxtAuthor().setEnabled(true);
        view.getTxtAuthor().setText(
                String.valueOf(view.getTblNotificacoes().getValueAt(linha, 0))
        );
        view.getTxtAuthor().setEnabled(false);

        view.getTxtSubject().setEnabled(true);
        view.getTxtSubject().setText(
                String.valueOf(view.getTblNotificacoes().getValueAt(linha, 1))
        );
        view.getTxtSubject().setEnabled(false);

        view.getTxtMenssage().setEnabled(true);
        view.getTxtMenssage().setText(table.getMensagem(linha)
        );
        view.getTxtMenssage().setEnabled(false);
        
        UsuarioLogadoService.getInstance().marcaComoLida(table.getNotification(linha));
    }
    
}
