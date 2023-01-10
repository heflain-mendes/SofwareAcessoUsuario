/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications.command;

import com.ufes.sofwareacessousuario.presenter.notifications.NotificacaoTable;
import com.ufes.sofwareacessousuario.view.NotificacaoView;

/**
 *
 * @author Heflain
 */
public interface NotificacaoCommand {
    public void executar(NotificacaoView view, NotificacaoTable table);
}
