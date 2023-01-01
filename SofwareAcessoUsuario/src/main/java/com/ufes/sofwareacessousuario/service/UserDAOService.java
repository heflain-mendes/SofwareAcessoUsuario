/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.model.SystemLog;
import com.ufes.sofwareacessousuario.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author heflainrmendes
 */
public class UserDAOService {

    private static final List<User> listUsers = new ArrayList();

    static {
        listUsers.add(new User(1, "heflain", "", User.AUTORIZED, User.ADMINISTERED));
        listUsers.add(new User(2, "abel", "", User.UNAUTORIZED, User.USER));
    }

    public static long getQtdUserRegistered() {
        return listUsers.size();
    }

    public static List<User> getUsers() {
        List<User> list = new ArrayList();
        long id = UserLoggedService.getId();
        for(var u : listUsers){
            if(u.getId() != id){
                list.add(u);
            }
        }
        
        return list;
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
        int type;

        if (getQtdUserRegistered() > 0) {
            type = User.USER;
            if (UserLoggedService.userLogged()
                    && UserLoggedService.getType() == User.ADMINISTERED) {

                state = User.AUTORIZED;

                LogService.escrever(new SystemLog(
                        LogService.INCLUSAO,
                        name,
                        LocalDateTime.now(),
                        UserLoggedService.getNome()
                ));
            } else {
                state = User.UNAUTORIZED;

                LogService.escrever(new SystemLog(
                        LogService.INCLUSAO,
                        name,
                        LocalDateTime.now(),
                        ""
                ));
            }
        } else {
            state = User.AUTORIZED;
            type = User.ADMINISTERED;

            LogService.escrever(new SystemLog(
                    LogService.INCLUSAO,
                    name,
                    LocalDateTime.now(),
                    ""
            ));
        }

        listUsers.add(new User(listUsers.size() + 1, name, password, state, type));

    }

    public static void updatePassword(String password) {

        LogService.escrever(new SystemLog(
                LogService.ALTERACAO_SENHA,
                UserLoggedService.getNome(),
                LocalDateTime.now(),
                UserLoggedService.getNome()
        ));

        var id = UserLoggedService.getId();

        for (var u : listUsers) {
            if (u.getId() == id) {
                var user = new User(
                        id,
                        u.getName(),
                        password,
                        u.getState(),
                        u.getType()
                );

                listUsers.remove(u);
                listUsers.add(user);
                UserLoggedService.login(user);

                break;
            }
        }
    }

    public static boolean nomeEmUso(String nome) {
        for (var u : listUsers) {
            if (u.getName().equals(nome)) {
                return true;
            }
        }

        return false;
    }

    public static void autorizarUsuario(long id) {
        for (var u : getUsers()) {
            if (u.getId() == id) {
                u.setState(User.AUTORIZED);

                LogService.escrever(new SystemLog(
                        LogService.AUTORIZACAO_USUARIO,
                        u.getName(),
                        LocalDateTime.now(),
                        UserLoggedService.getNome()
                ));

                break;
            }

        }
    }

    public static void removeUser(long id) {
        for (var u : getUsers()) {
            if (u.getId() == id) {
                listUsers.remove(u);

                LogService.escrever(new SystemLog(
                        LogService.EXCLUSAO,
                        u.getName(),
                        LocalDateTime.now(),
                        UserLoggedService.getNome()
                ));

                break;
            }

        }
    }

    public static User getUsuario(long id) {
        for (var u : listUsers) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public static String getNameUsuario(long id) {
        for (var u : listUsers) {
            if (u.getId() == id) {
                return u.getName();
            }
        }

        return null;
    }
}
