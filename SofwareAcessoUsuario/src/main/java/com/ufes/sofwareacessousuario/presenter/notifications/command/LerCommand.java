/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.presenter.notifications.NotificacaoTable;
import com.ufes.sofwareacessousuario.dao.service.NotificacaoRetorno;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.view.NotificacaoView;

/**
 *
 * @author Heflain
 */
public class LerCommand implements NotificacaoCommand {

    @Override
    public void executar(NotificacaoView view, NotificacaoTable table) {
        int linha = view.getTblNotificacoes().getSelectedRow();
        if (linha == -1) {
            return;
        }

        view.getTxtAutor().setEnabled(true);
        view.getTxtAutor().setText(
                String.valueOf(view.getTblNotificacoes().getValueAt(linha, 0))
        );
        view.getTxtAutor().setEnabled(false);

        view.getTxtSujeito().setEnabled(true);
        view.getTxtSujeito().setText(
                String.valueOf(view.getTblNotificacoes().getValueAt(linha, 1))
        );
        view.getTxtSujeito().setEnabled(false);

        view.getTxtMenssagem().setEnabled(true);
        view.getTxtMenssagem().setText(table.getMensagem(linha)
        );
        view.getTxtMenssagem().setEnabled(false);
        
        UsuarioLogadoService.getInstance().marcaComoLida(table.getNotification(linha));
    }
    
}
