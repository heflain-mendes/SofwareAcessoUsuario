/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.dao.UserRetorno;
import com.ufes.sofwareacessousuario.presenter.principal.command.UpdateSizeNotificationCommand;
import com.ufes.sofwareacessousuario.dao.UsuarioLogadoService;

/**
 *
 * @author Heflain
 */
public class LoadBottomPanelState extends PrincipalPresenterState {

    public LoadBottomPanelState(PrincipalPresenter presenter) {
        super(presenter);

        new UpdateSizeNotificationCommand(presenter.view).executar();

        presenter.view.getLblUserName().setText(UsuarioLogadoService.getInstance().getNome()
        );

        int tipo = UsuarioLogadoService.getInstance().getType();

        presenter.view.getLblUserType().setText(tipo == UserRetorno.ADMINISTERED ? "ADMINISTRADOR" : "USER"
        );
        
        presenter.view.getPnlBottom().setVisible(true);

        if (tipo == UserRetorno.ADMINISTERED) {
            new AdminLoggedState(presenter);
        } else {
            new UserLoggedState(presenter);
        }
    }

    @Override
    public void changePassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void viewNotification() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void config() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
