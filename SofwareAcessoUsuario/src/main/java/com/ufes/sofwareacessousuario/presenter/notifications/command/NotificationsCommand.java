/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.presenter.notifications.NotificationTable;
import com.ufes.sofwareacessousuario.view.NotificationsView;

/**
 *
 * @author Heflain
 */
public interface NotificationsCommand {
    public void executar(NotificationsView view, NotificationTable table);
}
