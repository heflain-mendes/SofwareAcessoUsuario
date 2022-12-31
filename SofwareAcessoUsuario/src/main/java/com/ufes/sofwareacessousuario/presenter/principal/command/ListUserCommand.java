/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.listusers.ListUserPresenter;

/**
 *
 * @author Heflain
 */
public class ListUserCommand implements Command{

    @Override
    public void executar() {
        new ListUserPresenter();
    }
    
}
