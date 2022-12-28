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
    private long id;
    private long idRemetente;
    private long idReceptor;
    private String mensagem;

    public Notification(long id, long idRemetente, long idReceptor, String mensagem) {
        this.id = id;
        this.idRemetente = idRemetente;
        this.idReceptor = idReceptor;
        this.mensagem = mensagem;
    }

    public Notification(long idRemetente, long idReceptor, String mensagem) {
        this.idRemetente = idRemetente;
        this.idReceptor = idReceptor;
        this.mensagem = mensagem;
    }

    public long getId() {
        return id;
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
}
