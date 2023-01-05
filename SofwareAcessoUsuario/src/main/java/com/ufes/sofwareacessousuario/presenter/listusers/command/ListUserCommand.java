/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.listusers.UserTable;
import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;
import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public abstract class ListUserCommand implements Command{
    ListUserPresenter presenter;
    ListUserView view;
    UserTable model;

    public ListUserCommand(ListUserPresenter presenter, ListUserView view, UserTable model) {
        this.presenter = presenter;
        this.view = view;
        this.model = model;
    }
}
