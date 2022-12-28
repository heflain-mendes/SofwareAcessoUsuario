/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

/**
 *
 * @author heflainrmendes
 */
public class User {
    //vou deixar int pois fica melhor caso novos state e type forem adicionados
    public final static int UNAUTORIZED = 0;
    public final static int AUTORIZED = 1;
    
    public final static int USER = 0;
    public final static int ADMINISTERED = 1;
    
    private long id;
    private String name;
    private String password;
    private int type;
    private int state;

    public User(long id, String name, String password, int state, int type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.state = state;
        this.type = type;
    }
    
    public User(String nome, String senha, int state) {
        this.name = nome;
        this.password = senha;
        this.state = state;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getState() {
        return state;
    }

    public int getType() {
        return type;
    }

    public void setState(int state) {
        this.state = state;
    }
}
