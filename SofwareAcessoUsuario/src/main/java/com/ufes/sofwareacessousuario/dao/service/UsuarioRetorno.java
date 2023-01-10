/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.model.UsuarioRegistro;

/**
 *
 * @author heflainrmendes
 */
public class UsuarioRetorno {
    public final static String DESAUTORIZADO = "Não autorizado";
    public final static String AUTORIZADO = "Autorizado";
    
    public final static String USUARIO = "Usuario";
    public final static String ADMINISTRADOR = "Administrador";
    
    private long id;
    private String nome;
    private String tipo;
    private String estado;

    public UsuarioRetorno(long id, String nome, int estado, int tipo) {
        this.id = id;
        this.nome = nome;
        if(estado == UsuarioRegistro.AUTORIZADO){
            this.estado = AUTORIZADO;
        }else{
            this.estado = DESAUTORIZADO;
        }
        
        if(tipo == UsuarioRegistro.ADMINISTRADOR){
            this.tipo = ADMINISTRADOR;
        }else{
            this.tipo = USUARIO;
        }
    }
    
    public UsuarioRetorno(UsuarioRetorno user){
        this.id = user.id;
        this.nome = user.nome;
        this.estado = user.estado;
        this.tipo = user.tipo;
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipo() {
        return tipo;
    }

    void setEstado(String state) {
        this.estado = state;
    }
}
