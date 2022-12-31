/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.presenter.notifications.command.LerCommand;

/**
 *
 * @author Heflains
 */
public class TabelaCarregada extends NotificationsPresenterState {

    public TabelaCarregada(NotificationsPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnLer().setEnabled(true);
    }

    @Override
    public void ler() {
        new LerCommand().executar(presenter.view, presenter.table);
    }
}
