/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.notifications.NotificacaoTable;
import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.view.NotificacaoView;

/**
 *
 * @author Heflain
 */
public class LerCommand implements Command {
    private NotificacaoView view;
    private NotificacaoTable table;

    public LerCommand(NotificacaoView view, NotificacaoTable table) {
        this.table = table;
        this.view = view;
    }

    @Override
    public void executar() {
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
        
        UsuarioLogadoServiceProxy.getInstance().marcaComoLida(table.getNotification(linha));
    }
    
}
