/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

/**
 *
 * @author Heflain
 */
public abstract class NotificacaoPresenterState {
    NotificacaoPresenter presenter;

    public NotificacaoPresenterState(NotificacaoPresenter presenter) {
        this.presenter = presenter;
        presenter.setState(this);
    }
    
    public abstract void ler();
}
