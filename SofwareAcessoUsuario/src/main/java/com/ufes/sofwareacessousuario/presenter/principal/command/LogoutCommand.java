/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.UserNotLoggedState;
import com.ufes.sofwareacessousuario.service.UserLoggedService;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author Heflain
 */
public class LogoutCommand implements Command{
    private PrincipalPresenter presenter;
    private PrincipalView view;

    public LogoutCommand(PrincipalPresenter presenter, PrincipalView view) {
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void executar() {
        view.getPnlPrincipal().removeAll();
        UserLoggedService.logout();
        new UserNotLoggedState(presenter);
    }
}
