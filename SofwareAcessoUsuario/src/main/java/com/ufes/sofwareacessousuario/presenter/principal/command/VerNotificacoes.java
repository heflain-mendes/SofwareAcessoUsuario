/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.notifications.NotificacaoPresenter;

/**
 *
 * @author Heflain
 */
public class VerNotificacoes implements Command{

    public void executar() {
        new NotificacaoPresenter();
    }
}
