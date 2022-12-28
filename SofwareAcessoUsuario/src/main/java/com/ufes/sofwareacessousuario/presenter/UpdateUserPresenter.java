/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.service.LoggedUserService;
import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.service.UserDAOService;
import com.ufes.sofwareacessousuario.validacaosenha.ValidadorSenha;
import com.ufes.sofwareacessousuario.view.RegisterUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendes
 */
public class UpdateUserPresenter {

    private RegisterUserView view;

    public UpdateUserPresenter() {
        view = new RegisterUserView();
        view.setTitle("Atualizar senha");
        
        view.getLblInvalidName().setVisible(false);
        view.getLblInvalidPassword().setVisible(false);
        view.getLblNomeUsuarioUso().setVisible(false);
        
        view.getTxtUserName().setText(LoggedUserService.getNome());
        view.getTxtUserName().setEnabled(false);
        
        view.getBtnRegistre().setText("Atualizar");
        view.getBtnRegistre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        PrincipalViewService.add(view);
        view.setVisible(true);
    }
    
    private void atualizar(){
        var senha = String.valueOf( view.getTxtPassword().getPassword());
        var confirmaSenha = String.valueOf(view.getTxtConfirmPassword().getPassword());
        
        if(senha.equals(confirmaSenha)){
            view.getLblInvalidPassword().setVisible(false);
            var recusas= new ValidadorSenha().verificar(senha);
            
            if(recusas.size() == 0){
                UserDAOService.updatePassword(senha);
            }
            
        }else{
            view.getLblInvalidPassword().setVisible(true);
        }
    }
    
    private void fechar(){
        view.dispose();
    }
}
