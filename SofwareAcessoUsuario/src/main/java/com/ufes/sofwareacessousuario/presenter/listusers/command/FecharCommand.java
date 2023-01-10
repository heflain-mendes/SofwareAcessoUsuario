/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author Heflain
 */
public class FecharCommand extends ListarUsuariosCommand{

    public FecharCommand(ListaUsuarioPresenter presenter, ListaUsuarioView view, UsuarioTable model) {
        super(presenter, view, model);
    }

    @Override
    public void executar() {
        if(view != null){
            view.dispose();
        }
    }
    
}
