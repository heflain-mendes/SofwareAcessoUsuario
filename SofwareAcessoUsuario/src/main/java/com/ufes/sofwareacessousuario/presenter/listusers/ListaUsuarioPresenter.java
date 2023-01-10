/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.ListaUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

/**
 *
 * @author heflainrmendes
 */
public class ListaUsuarioPresenter {
    
    private ListaUsuarioView view;
    private UsuarioTable model;
    private ListUserPresenterState state;
    
    public ListaUsuarioPresenter() {
        view = new ListaUsuarioView();
        model = new UsuarioTable();
        view.getTblUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTblUsuarios().setModel(model);
        model.setUsers(UsuariosDAOService.getInstance().getUsers());
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
        
        view.getBtnfechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
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
    
    private void fechar(){
        state.fechar();
    }
    
    private void enviarNotificacao() {
        state.enviarNotificacao();
    }

    protected ListaUsuarioView getView() {
        return view;
    }

    UsuarioTable getModel() {
        return model;
    }

    public void setState(ListUserPresenterState state) {
        this.state = state;
    }
}
