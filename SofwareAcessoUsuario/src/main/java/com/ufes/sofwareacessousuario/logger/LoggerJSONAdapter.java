/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogJSONAdapter;
import com.mycompany.model.Log;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerJSONAdapter extends LoggerAdapter{
    LogJSONAdapter adapter;
    
    public LoggerJSONAdapter(String caminho) {
        adapter = new LogJSONAdapter(openFile(caminho + ".json"));
    }
    

    @Override
    public void escrever(Log... log) throws IOException {
        adapter.escrever(log);
    }

    @Override
    public List<Log> exportaTodos() throws IOException {
        return adapter.exportaTodos();
    }
}
