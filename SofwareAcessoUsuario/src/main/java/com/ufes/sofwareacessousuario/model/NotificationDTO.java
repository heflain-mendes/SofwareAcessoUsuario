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
public class NotificationDTO {
    private long id;
    private long idRemetente;
    private String assunto;
    private String mensagem;
    private int estado;

    public NotificationDTO(long id, long idRemetente, String assunto, String mensagem, int estado) {
        this.id = id;
        this.idRemetente = idRemetente;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.estado = estado;
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

    public String getMensagem() {
        return mensagem;
    }

    public int getEstado() {
        return estado;
    }
}
