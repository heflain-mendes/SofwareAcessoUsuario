/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.ListUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author heflainrmendes
 */
public class ListUserPresenter {
    
    private ListUserView view;
    private DefaultTableModel model;
    private ListUserPresenterState state;
    
    public ListUserPresenter() {
        view = new ListUserView();
        view.getTblUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        view.getBtnAutorizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autorizar();
            }
        });
        
        view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remover();
            }
        });
        
        view.getBtnEnviarNotificacao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarNotificacao();
            }
        });
        
        new CarregandoTabelaState(this);
        
        PrincipalViewService.add(view);
        view.setVisible(true);
    }
    
    private void autorizar() {
        state.autorizar();
    }
    
    private void remover() {
        state.remover();
    }
    
    private void enviarNotificacao() {
        state.enviarNotificacao();
    }

    protected ListUserView getView() {
        return view;
    }

    DefaultTableModel getModel() {
        return model;
    }

    public void setState(ListUserPresenterState state) {
        this.state = state;
    }
}
