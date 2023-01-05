/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.UserTable;
import com.ufes.sofwareacessousuario.presenter.SendNotificationPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public class EnviarNotificacaoCommand extends ListUserCommand {

    public EnviarNotificacaoCommand(ListUserPresenter presenter, ListUserView view, UserTable model) {
        super(presenter, view, model);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }

        String id = String.valueOf(view.getTblUsuarios().getValueAt(
                view.getTblUsuarios().getSelectedRow(), 0)
        );

        new SendNotificationPresenter(Long.parseLong(id));
    }

}
