/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogJSONAdapter;
import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.model.SystemLog;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerJSONAdapter extends LoggerAdapter{
    private LoggerConversorMyProjectAdapter conversor;
    private LogJSONAdapter adapter;
    
    public LoggerJSONAdapter(String caminho) {
        conversor = new LoggerConversorMyProjectAdapter();
        adapter = new LogJSONAdapter(openFile(caminho + ".json"));
    }

   @Override
    public void escrever(SystemLog... log) throws IOException {      
        adapter.escrever(conversor.converteLog(log));
    }

    @Override
    public List<SystemLog> exportaTodos() throws IOException {
        return conversor.converteLog(adapter.exportaTodos().toArray(new Log[0]));
    }
}
