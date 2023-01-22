/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public abstract class PrincipalCommand implements Command {
    PrincipalPresenter principalPresenter;

    public PrincipalCommand(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
    }
}
