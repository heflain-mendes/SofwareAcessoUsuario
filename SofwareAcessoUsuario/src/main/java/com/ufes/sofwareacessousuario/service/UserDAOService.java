/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.model.User;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author heflainrmendes
 */
public class UserDAOService {

    private static final List<User> listUsers = new ArrayList();

    public static long getQtdUserRegistered() {
        return listUsers.size();
    }

    public static User login(String name, String password) {
        for (var u : listUsers) {
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                return u;
            }
        }

        return null;
    }

    public static void registered(String name, String password) {
        int state;
        int type = getQtdUserRegistered() > 0 ? User.USER : User.ADMINISTERED;

        if (getQtdUserRegistered() > 0) {
            type = User.USER;
            if (LoggedUserService.getUser() == null) {
                state = User.UNAUTORIZED;
            } else {
                if (LoggedUserService.getUser().getType() == User.USER) {
                    throw new RuntimeException("Falha de segurança, usuario USER "
                            + "está podendo cadastra novos usuários");
                }
                state = User.AUTORIZED;
            }
        } else {
            state = User.AUTORIZED;
            type = User.ADMINISTERED;
        }

        listUsers.add(new User(listUsers.size() + 1, name, password, state, type));
    }
}
