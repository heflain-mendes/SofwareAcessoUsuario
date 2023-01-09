/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.UserTable;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author Heflain
 */
public class FecharCommand extends ListUserCommand{

    public FecharCommand(ListUserPresenter presenter, ListUserView view, UserTable model) {
        super(presenter, view, model);
    }

    @Override
    public void executar() {
        if(view != null){
            view.dispose();
        }
    }
    
}
