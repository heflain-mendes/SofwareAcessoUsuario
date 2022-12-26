/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.observable.fileConfigService;

/**
 *
 * @author heflainrmendes
 */
public interface EventListerners {
    public static final String LOAD_FILE = "LOAD_FILE";
    public static final String UPDATE_TYPE_LOG = "UPDATE_TYPE_LOG";
    
    public void update(String mensagem, String info);
}
