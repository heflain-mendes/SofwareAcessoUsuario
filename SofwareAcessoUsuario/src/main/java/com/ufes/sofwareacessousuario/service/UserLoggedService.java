/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.model.User;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;

/**
 *
 * @author heflainrmendes
 */
public class UserLoggedService {

    public static String USER_LOGGED = "user logged";
    public static String USER_UNLOGGED = "user unlogged";

    private static User user;
    private static EventManager eventManager = new EventManager();

    public static boolean userLogged() {
        return user != null;
    }

    public static String getNome() {
        return user.getName();
    }

    public static long getId() {
        return user.getId();
    }

    public static int getType() {
        return user.getType();
    }

    public static int getState() {
        return user.getState();
    }

    public static void login(User user) {
        if (UserLoggedService.user == null) {
            UserLoggedService.user = user;
            eventManager.notify(USER_LOGGED);
        }
    }
    
    public static void logout() {
        if (UserLoggedService.user != null) {
            UserLoggedService.user = null;
            eventManager.notify(USER_UNLOGGED);
        }
    }

    public static void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public static void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }
}
