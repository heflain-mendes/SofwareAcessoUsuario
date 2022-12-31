/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

/**
 *
 * @author Heflain
 */
public abstract class NotificationsPresenterState {
    NotificationsPresenter presenter;

    public NotificationsPresenterState(NotificationsPresenter presenter) {
        this.presenter = presenter;
        presenter.setState(this);
    }
    
    public abstract void ler();
}
