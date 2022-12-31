/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

/**
 *
 * @author heflainrmendes
 */
public abstract class PrincipalPresenterState {
    PrincipalPresenter presenter;

    public PrincipalPresenterState(PrincipalPresenter presenter) {
        this.presenter = presenter;
        presenter.setState(this);
    }
    
    public abstract void changePassword();
    public abstract void viewNotification();
    public abstract void addUser();
    public abstract void listUser();
    public abstract void config();
    public abstract void logout();
}
