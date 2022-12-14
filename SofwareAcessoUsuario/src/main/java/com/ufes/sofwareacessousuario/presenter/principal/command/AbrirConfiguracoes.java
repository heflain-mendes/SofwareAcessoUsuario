/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.ConfiguracaoPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public class AbrirConfiguracoes extends PrincipalCommand{

    public AbrirConfiguracoes(PrincipalPresenter principalPresenter) {
        super(principalPresenter);
    }
    
    @Override
    public void executar() {
        new ConfiguracaoPresenter(principalPresenter);
    }
}
