/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.UsuarioDeslogado;
import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author Heflain
 */
public class Deslogar extends PrincipalCommand{
    private PrincipalView view;

    public Deslogar(PrincipalPresenter principalPresenter, PrincipalView view) {
        super(principalPresenter);
        this.view = view;
    }

    @Override
    public void executar() {
        view.getPnlPrincipal().removeAll();
        UsuarioLogadoServiceProxy.getInstance().logout();
        new UsuarioDeslogado(principalPresenter);
    }
}
