/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.command.AddUserCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.ChangePasswordCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.ConfigCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.ListUserCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.LogoutCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.UpdateSizeNotificationCommand;
import com.ufes.sofwareacessousuario.presenter.principal.command.ViewNotificationCommand;
import com.ufes.sofwareacessousuario.service.NotificationDAOService;

/**
 *
 * @author Heflain
 */
public class AdminLoggedState extends PrincipalPresenterState implements EventListerners{

    public AdminLoggedState(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAdmin().setVisible(true);
        presenter.view.getBtnAdmin().setEnabled(true);
        
        presenter.view.getBtnUser().setVisible(true);
        presenter.view.getBtnUser().setEnabled(true);
        
        NotificationDAOService.subcribe(this);
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
        new AddUserCommand().executar();
    }

    @Override
    public void listUser() {
        new ListUserCommand().executar();
    }

    @Override
    public void config() {
        new ConfigCommand().executar();
    }

    @Override
    public void logout() {
        new LogoutCommand(presenter, presenter.view).executar();
        NotificationDAOService.unsubcribe(this);
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(NotificationDAOService.DECRWMENT_NOTIFICATION)){
            new UpdateSizeNotificationCommand(presenter.view).executar();
        }
    }
}
