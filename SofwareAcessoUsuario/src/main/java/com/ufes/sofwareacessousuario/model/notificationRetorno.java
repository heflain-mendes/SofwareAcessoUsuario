/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

import static com.ufes.sofwareacessousuario.model.Notification.NAO_LIDO;

/**
 *
 * @author Heflain
 */
public class notificationRetorno {
    public static String LIDO = "SIM";
    public static String NAO_LIDO = "NÂO";
    
    private long id;
    private String remetente;
    private String receptor;
    private String assunto;
    private String mensagem;
    private String estado;

    public notificationRetorno(long id, String remetente, String receptor, String assunto, String mensagem, String estado) {
        this.id = id;
        this.remetente = remetente;
        this.receptor = receptor;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getEstado() {
        return estado;
    }

    /**
     * Set adicionado para diminuir o custo de processamento para atualizar tables
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
