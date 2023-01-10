/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.presenter.EnviarNotificacaoPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author heflainrmendes
 */
public class EnviarNotificacaoCommand extends ListarUsuariosCommand {

    public EnviarNotificacaoCommand(ListaUsuarioPresenter presenter, ListaUsuarioView view, UsuarioTable model) {
        super(presenter, view, model);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }

        String id = String.valueOf(view.getTblUsuarios().getValueAt(
                view.getTblUsuarios().getSelectedRow(), 0)
        );

        new EnviarNotificacaoPresenter(Long.parseLong(id));
    }

}
