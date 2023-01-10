/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal.command;

import com.ufes.sofwareacessousuario.command.Command;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author Heflain
 */
public class AtualizarNumeroDeNotificacoes implements Command{
    private PrincipalView view;

    public AtualizarNumeroDeNotificacoes(PrincipalView view) {
        this.view = view;
    }

    public void executar() {
        view.getBtnQtdNotificacoes().setText(String.valueOf(UsuarioLogadoService.getInstance().getQtdNotificacoesNaoLida()));
    }
    
}
