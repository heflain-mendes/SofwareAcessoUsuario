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

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoggedUserService.user = user;
    }
}
