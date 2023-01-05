/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.dao.UsuarioLogadoService;

/**
 *
 * @author Heflain
 */
public class CarregandoTabela extends NotificationsPresenterState{

    public CarregandoTabela(NotificationsPresenter presenter) {
        super(presenter);
        
        presenter.view.getTxtAuthor().setEnabled(false);
        presenter.view.getTxtMenssage().setEnabled(false);
        presenter.view.getTxtSubject().setEnabled(false);
        
        presenter.view.getBtnLer().setEnabled(false);
        
        presenter.table.setList(
                UsuarioLogadoService.getInstance().getNotifications());

        presenter.view.getTblNotificacoes().setModel(presenter.table);
        
        new TabelaCarregada(presenter);
    }

    @Override
    public void ler() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
