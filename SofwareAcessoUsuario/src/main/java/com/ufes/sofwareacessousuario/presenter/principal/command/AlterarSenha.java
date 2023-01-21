/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.AtualizarUsuarioPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflain
 */
public class AlterarSenha extends PrincipalCommand{

    public AlterarSenha(PrincipalPresenter principalPresenter) {
        super(principalPresenter);
    }
    @Override
    public void executar() {
        new AtualizarUsuarioPresenter(principalPresenter);
    }
}
