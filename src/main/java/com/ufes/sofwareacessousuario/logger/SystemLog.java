/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author heflainrmendes
 */
public class SystemLog {
    private static DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private String operacao;
    private String nomeContato;
    private String dataHora;
    private String nomeUsuario;

    public SystemLog(String operacao, String nomecontato, LocalDateTime dataHora, String nomeUsuario) {
        this.operacao = operacao;
        this.nomeContato = nomecontato;
        this.dataHora = dataHora.format(formatoDataHora);
        this.nomeUsuario = nomeUsuario;
    }

    public static void setFormatoEscritaDataHora(DateTimeFormatter formatoDataHora) {
        if (formatoDataHora != null) {
            formatoDataHora = formatoDataHora;
        }
    }

    public static DateTimeFormatter getFormatoDataHora() {
        return formatoDataHora;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getNomecontato() {
        return nomeContato;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
