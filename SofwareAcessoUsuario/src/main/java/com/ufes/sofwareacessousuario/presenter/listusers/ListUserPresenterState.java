/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.view.ListUserView;

/**
 *
 * @author heflainrmendes
 */
public abstract class ListUserPresenterState {
    protected ListUserPresenter presenter;
    protected ListUserView view;
    protected UserTable model;

    public ListUserPresenterState(ListUserPresenter presenter) {
        this.presenter = presenter;
        this.view = presenter.getView();
        this.model = presenter.getModel();
        this.presenter.setState(this);
    }
    
    public abstract void autorizar();
    public abstract void remover();
     public abstract void fechar();
    public abstract void enviarNotificacao();
}
