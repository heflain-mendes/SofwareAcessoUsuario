/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;

/**
 *
 * @author Heflain
 */
public class CarregandoTabela extends NotificacaoPresenterState{

    public CarregandoTabela(NotificacaoPresenter presenter) {
        super(presenter);
        
        presenter.view.getTxtAutor().setEnabled(false);
        presenter.view.getTxtMenssagem().setEnabled(false);
        presenter.view.getTxtSujeito().setEnabled(false);
        
        presenter.view.getBtnLer().setEnabled(false);
        
        presenter.table.setList(UsuarioLogadoService.getInstance().getNotifications());

        presenter.view.getTblNotificacoes().setModel(presenter.table);
        
        new TabelaCarregada(presenter);
    }

    @Override
    public void ler() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
