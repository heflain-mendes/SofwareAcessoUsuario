/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author heflainrmendes
 */
public abstract class ListarUsuariosCommand implements Command{
    ListaUsuarioPresenter presenter;
    ListaUsuarioView view;
    UsuarioTable model;

    public ListarUsuariosCommand(ListaUsuarioPresenter presenter, ListaUsuarioView view, UsuarioTable model) {
        this.presenter = presenter;
        this.view = view;
        this.model = model;
    }
}
