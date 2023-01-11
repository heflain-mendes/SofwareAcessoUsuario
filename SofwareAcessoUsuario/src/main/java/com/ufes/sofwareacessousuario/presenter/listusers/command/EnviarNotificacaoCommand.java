/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.presenter.EnviarNotificacaoPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author heflainrmendes
 */
public class EnviarNotificacaoCommand extends ListarUsuariosCommand {
    private PrincipalPresenter principalPresenter;

    public EnviarNotificacaoCommand(
            ListaUsuarioPresenter presenter,
            ListaUsuarioView view,
            UsuarioTable model,
            PrincipalPresenter principalPresenter
    ) {
        super(presenter, view, model);
        this.principalPresenter = principalPresenter;
        
        
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }
        
        model.getUser(view.getTblUsuarios().getSelectedRow());

        new EnviarNotificacaoPresenter(
                model.getUser(view.getTblUsuarios().getSelectedRow()),
                principalPresenter
        );
    }
}
