/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.presenter.principal.UsuarioDeslogado;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author Heflain
 */
public class Deslogar implements Command{
    private PrincipalPresenter presenter;
    private PrincipalView view;

    public Deslogar(PrincipalPresenter presenter, PrincipalView view) {
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void executar() {
        view.getPnlPrincipal().removeAll();
        UsuarioLogadoService.getInstance().logout();
        new UsuarioDeslogado(presenter);
    }
}
