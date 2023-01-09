/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.model.UserRegistro;

/**
 *
 * @author heflainrmendes
 */
public class UserRetorno {
    public final static String UNAUTORIZED = "Não autorizado";
    public final static String AUTORIZED = "Autorizado";
    
    public final static String USER = "Usuario";
    public final static String ADMINISTERED = "Administrador";
    
    private long id;
    private String name;
    private String type;
    private String state;

    public UserRetorno(long id, String name, int state, int type) {
        this.id = id;
        this.name = name;
        if(state == UserRegistro.AUTORIZED){
            this.state = AUTORIZED;
        }else{
            this.state = UNAUTORIZED;
        }
        
        if(type == UserRegistro.ADMINISTERED){
            this.type = ADMINISTERED;
        }else{
            this.type = USER;
        }
    }
    
    public UserRetorno(UserRetorno user){
        this.id = user.id;
        this.name = user.name;
        this.state = user.state;
        this.type = user.type;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    void setState(String state) {
        this.state = state;
    }
}
