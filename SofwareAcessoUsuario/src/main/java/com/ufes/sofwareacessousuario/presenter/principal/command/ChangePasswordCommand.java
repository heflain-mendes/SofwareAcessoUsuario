/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.ConfigurationPresenter;
import com.ufes.sofwareacessousuario.presenter.RegisterUserPresenter;

/**
 *
 * @author Heflain
 */
public class ChangePasswordCommand implements Command{

    @Override
    public void executar() {
        new RegisterUserPresenter();
    }
    
}
