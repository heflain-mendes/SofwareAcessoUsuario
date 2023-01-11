/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.RegistrarUsuarioPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public class AdicionarUsuario extends PrincipalCommand{

    public AdicionarUsuario(PrincipalPresenter principalPresenter) {
        super(principalPresenter);
    }

    @Override
    public void executar() {
        new RegistrarUsuarioPresenter(principalPresenter);
    }
    
}
