/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.view.NotificacaoView;

/**
 *
 * @author Heflain
 */
public class FecharCommand implements Command {
    private NotificacaoView view;
    private PrincipalPresenter principalPresenter;
    
    public FecharCommand(NotificacaoView view, PrincipalPresenter principalPresente) {
        this.view = view;
        this.principalPresenter = principalPresente;
    }

    @Override
    public void executar() {
        view.dispose();
        principalPresenter.removerView(view);
    }
}
