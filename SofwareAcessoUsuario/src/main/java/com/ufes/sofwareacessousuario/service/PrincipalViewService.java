/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.view.PrincipalView;

/**
 *
 * @author heflainrmendes
 */
public class PrincipalViewService {
    private static PrincipalView principalView;
    
    public static PrincipalView getPrincipalView(){
        if(principalView == null){
            throw new NullPointerException("principalView ainda não foi definido");
        }
        return PrincipalViewService.principalView;
    }
    
    public static void setPrincipalView(PrincipalView principalView){
        if(principalView == null){
             throw new NullPointerException("principalView não pode ser null");
        }
        PrincipalViewService.principalView = principalView;
    }
}
