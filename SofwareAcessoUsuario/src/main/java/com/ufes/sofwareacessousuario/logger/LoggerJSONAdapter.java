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
public class LoggerJSONAdapter extends LoggerMyProjectAdapter{
    LogJSONAdapter adapter;
    
    public LoggerJSONAdapter(String caminho) {
        adapter = new LogJSONAdapter(openFile(caminho + ".json"));
    }

   @Override
    public void escrever(SystemLog... log) throws IOException {      
        adapter.escrever(converteLog(log));
    }

    @Override
    public List<SystemLog> exportaTodos() throws IOException {
        return converteLog(adapter.exportaTodos().toArray(new Log[0]));
    }
}
