/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.NotificacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

/**
 *
 * @author heflainrmendes
 */
public class NotificacaoPresenter {

    NotificacaoView view;
    NotificacaoTable table;
    NotificacaoPresenterState state;

    public NotificacaoPresenter() {
        view = new NotificacaoView();
        table = new NotificacaoTable();
        
        view.getTblNotificacoes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        view.getBtnLer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ler();
            }
        });

        new CarregandoTabela(this);

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    public void setState(NotificacaoPresenterState state) {
        this.state = state;
    }

    private void ler() {
        state.ler();
    }
}