/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.notifications.NotificationsPresenter;

/**
 *
 * @author Heflain
 */
public class ViewNotificationCommand implements Command{

    @Override
    public void executar() {
        new NotificationsPresenter();
    }
}
