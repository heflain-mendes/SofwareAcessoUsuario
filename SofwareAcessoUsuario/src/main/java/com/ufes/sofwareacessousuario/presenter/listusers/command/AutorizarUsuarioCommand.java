/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.CarregandoTabelaState;
import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.service.UserDAOService;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public class AutorizarUsuarioCommand extends ListUserCommand {

    public AutorizarUsuarioCommand(ListUserPresenter presenter, ListUserView view) {
        super(presenter, view);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }
        
        System.out.println("cheguei");

        String id = String.valueOf(view.getTblUsuarios().getValueAt(
                view.getTblUsuarios().getSelectedRow(), 0)
        );
        UserDAOService.autorizarUsuario(Long.parseLong(id));

        new CarregandoTabelaState(presenter);
    }
}
