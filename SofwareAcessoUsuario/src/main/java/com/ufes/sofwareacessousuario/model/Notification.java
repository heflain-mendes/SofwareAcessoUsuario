/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

/**
 *
 * @author heflainrmendes
 */
public class Notification {
    public static int NAO_LIDO = 0;
    public static int LIDO = 1;
    
    private long id;
    private long idRemetente;
    private long idReceptor;
    private String assunto;
    private String mensagem;
    private int estado;

    public Notification(long id, long idRemetente, long idReceptor, String assunto, String mensagem, int estado) {
        this.id = id;
        this.idRemetente = idRemetente;
        this.idReceptor = idReceptor;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.estado = estado;
    }
    
    public Notification(long id, long idRemetente, long idReceptor, String assunto, String mensagem) {
        this.id = id;
        this.idRemetente = idRemetente;
        this.idReceptor = idReceptor;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.estado = NAO_LIDO;
    }

    public Notification(long idRemetente, long idReceptor, String mensagem) {
        this.idRemetente = idRemetente;
        this.idReceptor = idReceptor;
        this.mensagem = mensagem;
    }

    public long getId() {
        return id;
    }

    public String getAssunto() {
        return assunto;
    }

    public long getIdRemetente() {
        return idRemetente;
    }

    public long getIdReceptor() {
        return idReceptor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
