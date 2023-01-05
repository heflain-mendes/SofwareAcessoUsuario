/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.dao.UserRetorno;
import com.ufes.sofwareacessousuario.presenter.listusers.UserTable;
import com.ufes.sofwareacessousuario.presenter.listusers.CarregandoTabelaState;
import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.dao.UsersDAOService;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public class AutorizarUsuarioCommand extends ListUserCommand {

    public AutorizarUsuarioCommand(ListUserPresenter presenter, ListUserView view, UserTable model) {
        super(presenter, view, model);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }

        System.out.println(view.getTblUsuarios().getSelectedRow());

        if(model == null){
            System.out.println("model é null");
        }
        
        UserRetorno u = model.getUser(
                view.getTblUsuarios().getSelectedRow()
        );
        
        System.out.println(u.getName());
        
        UsersDAOService.getInstance().autorizarUsuario(u);

        new CarregandoTabelaState(presenter);
    }
}
