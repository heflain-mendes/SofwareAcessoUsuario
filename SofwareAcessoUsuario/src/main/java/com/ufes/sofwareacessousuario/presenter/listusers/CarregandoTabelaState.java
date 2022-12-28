/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.model.User;
import com.ufes.sofwareacessousuario.service.UserDAOService;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author heflainrmendes
 */
public class CarregandoTabelaState extends ListUserPresenterState{

    public CarregandoTabelaState(ListUserPresenter presenter) {
        super(presenter);
        
        view.getBtnAutorizar().setEnabled(false);
        view.getBtnEnviarNotificacao().setEnabled(false);
        view.getBtnExcluir().setEnabled(false);
        
        model = new DefaultTableModel();
        view.getTblUsuarios().setModel(model);
        
        model.addColumn("id");
        model.addColumn("Nome");
        model.addColumn("Autorizado");
        
        for (var u : UserDAOService.getUsers()) {
            var state = u.getState() == User.AUTORIZED ? "Autorizado" : "não autorizado";
            model.addRow(new Object[]{u.getId(), u.getName(), state});
        }
        
        new TabelaCarregadaState(presenter);
    }

    @Override
    public void autorizar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void enviarNotificacao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
