/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.OptionAcessesPresenter;
import com.ufes.sofwareacessousuario.service.UserLoggedService;

/**
 *
 * @author Heflain
 */
public class UserNotLoggedState extends PrincipalPresenterState implements EventListerners{

    public UserNotLoggedState(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAdmin().setVisible(false);
        presenter.view.getBtnAdmin().setEnabled(false);
        
        presenter.view.getBtnUser().setVisible(false);
        presenter.view.getBtnUser().setEnabled(false);
        
        presenter.view.getPnlBottom().setVisible(false);
        
        UserLoggedService.subcribe(this);
        
        new OptionAcessesPresenter();
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

    @Override
    public void update(String mensagem) {
        if(UserLoggedService.USER_LOGGED.equals(mensagem)){
            UserLoggedService.unsubcribe(this);
            new LoadBottomPanelState(presenter);
        }
    }
}
