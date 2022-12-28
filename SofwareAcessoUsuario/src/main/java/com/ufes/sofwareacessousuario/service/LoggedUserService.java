/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.model.User;

/**
 *
 * @author heflainrmendes
 */
public class LoggedUserService {
    private static User user;

    public static boolean userLogged() {
        return user != null;
    }
    
    public static String getNome(){
        return user.getName();
    }
    
    public static Long getId(){
        return user.getId();
    }
    
    public static int getType(){
        return user.getType();
    }
    
    public static int getState(){
        return user.getState();
    }

    public static void setUser(User user) {
        LoggedUserService.user = user;
    }
}
