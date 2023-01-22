/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers.command;

import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import com.ufes.sofwareacessousuario.presenter.listusers.UsuarioTable;
import com.ufes.sofwareacessousuario.presenter.listusers.ListaUsuarioPresenter;
import com.ufes.sofwareacessousuario.util.UsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;

/**
 *
 * @author heflainrmendes
 */
public class AutorizarUsuarioCommand extends ListarUsuariosCommand {

    public AutorizarUsuarioCommand(ListaUsuarioPresenter presenter, ListaUsuarioView view, UsuarioTable model) {
        super(presenter, view, model);
    }

    public void executar() {
        if (view.getTblUsuarios().getSelectedRow() == -1) {
            return;
        }
        
        UsuarioRetorno u = model.getUser(
                view.getTblUsuarios().getSelectedRow()
        );
        
        UsuariosDAOServiceProxy.getInstance().autorizarUsuario(u);
        model.atualizarTabela();
    }
}
