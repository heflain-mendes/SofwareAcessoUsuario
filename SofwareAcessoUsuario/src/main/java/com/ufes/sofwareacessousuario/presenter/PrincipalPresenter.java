/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author heflainrmendes
 */
public class PrincipalPresenter {

    public PrincipalPresenter() {
        PrincipalView principalView = new PrincipalView();
        PrincipalViewService.setPrincipalView(principalView);
        
//        principalView.getBtnAdmin().setVisible(false);
//        principalView.getBtnUser().setVisible(false);
        
        principalView.setVisible(true);
    }
    
    private void iniciarView(){
        
    }
}
