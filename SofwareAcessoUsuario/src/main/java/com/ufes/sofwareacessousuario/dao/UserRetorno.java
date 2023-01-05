/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

/**
 *
 * @author heflainrmendes
 */
public class UserRetorno {
    public final static int UNAUTORIZED = 0;
    public final static int AUTORIZED = 1;
    
    public final static int USER = 0;
    public final static int ADMINISTERED = 1;
    
    private long id;
    private String name;
    private int type;
    private int state;

    public UserRetorno(long id, String name, int state, int type) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
    }
    
    public long getId() {
        return id;
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

    void setState(int state) {
        this.state = state;
    }
}
