/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.model.User;
import com.ufes.sofwareacessousuario.service.UserLoggedService;
import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.service.UserDAOService;
import com.ufes.sofwareacessousuario.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author heflainrmendes
 */
public class LoginPresenter {
    private LoginView view;

    public LoginPresenter() {
        this.view = new LoginView();
        
        view.getLblInvalidUserNameOrPassword().setVisible(false);
        
        view.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        PrincipalViewService.add(view);
        
        view.setVisible(true);
    }
    
    private void login(){
        String name = this.view.getTxtUserName().getText();
        String password = String.valueOf(this.view.getTxtPassword().getPassword());
        
        User user = UserDAOService.login(name, password);
        
        if(user == null){
            view.getLblInvalidUserNameOrPassword().setVisible(true);
        }else{
            UserLoggedService.login(user);
            view.dispose();
        }
    }
    
    private void close(){
        view.dispose();
        if (!UserLoggedService.userLogged()) {
            new OptionAcessesPresenter();
        }
    }
}
