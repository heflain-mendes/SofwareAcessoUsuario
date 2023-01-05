/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

/**
 *
 * @author Heflain
 */
public class UserRegistro {
    public final static int UNAUTORIZED = 0;
    public final static int AUTORIZED = 1;
    
    public final static int USER = 0;
    public final static int ADMINISTERED = 1;
    
    private String name;
    private int type;
    private int state;
    private String password;

    public UserRegistro(String name, String password, int state, int type) {
        this.name = name;
        this.state = state;
        this.type = type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getState() {
        return state;
    }

    public int getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }
}
