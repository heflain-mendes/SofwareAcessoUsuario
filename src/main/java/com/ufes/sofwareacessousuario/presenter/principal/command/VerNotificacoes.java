/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.notifications.NotificacaoPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public class VerNotificacoes extends PrincipalCommand{

    public VerNotificacoes(PrincipalPresenter principalPresenter) {
        super(principalPresenter);
    }
    @Override
    public void executar() {
        new NotificacaoPresenter(principalPresenter);
    }
}
