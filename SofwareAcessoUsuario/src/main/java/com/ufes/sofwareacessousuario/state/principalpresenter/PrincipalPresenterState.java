/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.state.principalpresenter;

import com.ufes.sofwareacessousuario.presenter.PrincipalPresenter;

/**
 *
 * @author heflainrmendes
 */
public abstract class PrincipalPresenterState {
    private PrincipalPresenter principalPresenter;

    public PrincipalPresenterState(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
    }
    
    public abstract void changePassword();
    public abstract void viewNotification();
    public abstract void sendNotification();
    public abstract void addUser();
    public abstract void authorizeUser();
    public abstract void config();
}
