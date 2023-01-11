/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.view.PrincipalView;
import java.awt.Component;

/**
 *
 * @author Heflain
 */
public class RemoverComponente implements Command{
    private PrincipalView view;
    private Component c;

    public RemoverComponente(PrincipalView view, Component c) {
        this.view = view;
        this.c = c;
    }

    @Override
    public void executar() {
        view.removerView(c);
    }
}
