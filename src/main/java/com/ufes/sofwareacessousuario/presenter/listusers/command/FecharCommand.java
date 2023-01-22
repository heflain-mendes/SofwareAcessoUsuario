/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author Heflain
 */
public class FecharCommand extends ListarUsuariosCommand{
    PrincipalPresenter principalPresenter;
    public FecharCommand(
            ListaUsuarioPresenter presenter, 
            ListaUsuarioView view,
            UsuarioTable model,
            PrincipalPresenter principalPresenter) {
        super(presenter, view, model);
        this.principalPresenter = principalPresenter;
    }

    @Override
    public void executar() {
        if(view != null){
            view.dispose();
            principalPresenter.removerView(view);
        }
    }
}
