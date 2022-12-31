/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.service.NotificationDAOService;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author Heflain
 */
public class UpdateSizeNotificationCommand implements Command{
    private PrincipalView view;

    public UpdateSizeNotificationCommand(PrincipalView view) {
        this.view = view;
    }

    public void executar() {
        view.getBtnAmountNotifications().setText(
                String.valueOf(NotificationDAOService.qtdNotifications())
        );
    }
    
}
