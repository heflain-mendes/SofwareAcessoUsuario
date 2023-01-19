/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public abstract class NotificacaoPresenterState {
    NotificacaoPresenter presenter;
    PrincipalPresenter principalPresenter;

    public NotificacaoPresenterState(
            NotificacaoPresenter presenter,
            PrincipalPresenter principalPresenter) {
        this.presenter = presenter;
        this.principalPresenter = principalPresenter;
        presenter.setState(this);
    }
    
    public abstract void ler();
    public abstract void fechar();
}
