/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

/**
 *
 * @author heflainrmendes
 */
public class CarregandoTabelaState extends ListUserPresenterState{

    public CarregandoTabelaState(ListaUsuarioPresenter presenter) {
        super(presenter);
        
        view.getBtnAutorizar().setEnabled(false);
        view.getBtnEnviarNotificacao().setEnabled(false);
        view.getBtnExcluir().setEnabled(false);
        
        model.atualizarTabela();
        
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

    @Override
    public void fechar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
