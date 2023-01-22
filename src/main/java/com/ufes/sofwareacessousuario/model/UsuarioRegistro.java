/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

/**
 *
 * @author Heflain
 */
public class UsuarioRegistro {
    public final static int DESAUTORIZADO = 0;
    public final static int AUTORIZADO = 1;
    
    public final static int USUARIO = 0;
    public final static int ADMINISTRADOR = 1;
    
    private String nome;
    private int tipo;
    private int estado;
    private String senha;

    public UsuarioRegistro(String nome, String senha, int estado, int tipo) {
        this.nome = nome;
        this.estado = estado;
        this.tipo = tipo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public int getEstado() {
        return estado;
    }

    public int getTipo() {
        return tipo;
    }

    public String getSenha() {
        return senha;
    }
}
