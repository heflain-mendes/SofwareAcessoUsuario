/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.dao.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.command.ChangePasswordCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.LogoutCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.UpdateSizeNotificationCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.ViewNotificationCommand;

/**
 *
 * @author Heflain
 */
public class UserLoggedState extends PrincipalPresenterState implements EventListerners{

    public UserLoggedState(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAdmin().setVisible(false);
        presenter.view.getBtnAdmin().setEnabled(false);
        
        presenter.view.getBtnUser().setVisible(true);
        presenter.view.getBtnUser().setEnabled(true);
        
        UsuarioLogadoService.getInstance().subcribe(this);
    }

    @Override
    public void changePassword() {
        new ChangePasswordCommand().executar();
    }

    @Override
    public void viewNotification() {
        new ViewNotificationCommand().executar();
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
        new LogoutCommand(presenter, presenter.view).executar();
        UsuarioLogadoService.getInstance().unsubcribe(this);
    }
    
    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoService.LIDA)){
            new UpdateSizeNotificationCommand(presenter.view).executar();
        }
    }
}
