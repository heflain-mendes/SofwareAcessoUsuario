/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.UserTable;
import com.ufes.sofwareacessousuario.presenter.listusers.CarregandoTabelaState;
import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.dao.UsersDAOService;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public class RemoverCommand extends ListUserCommand {

    public RemoverCommand(ListUserPresenter presenter, ListUserView view, UserTable model) {
        super(presenter, view, model);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }

        UsersDAOService.getInstance().removeUser(
                model.getUser(
                        view.getTblUsuarios().getSelectedRow()
                )
        );
        

        new CarregandoTabelaState(presenter);
    }
}
