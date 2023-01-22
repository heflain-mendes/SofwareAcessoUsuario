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
class LoggerJSONAdapter extends LoggerAdapter{
    private ConversorLog conversor;
    private LogJSONAdapter adapter;

   @Override
    public void escrever(SystemLog... log) throws IOException {      
        adapter.escrever(conversor.converterLog(log));
    }

    @Override
    public List<SystemLog> exportaTodos() throws IOException {
        return conversor.converterLog(adapter.exportaTodos().toArray(new Log[0]));
    }

    @Override
    public String getNome() {
        return "JSON";
    }

    @Override
    LoggerAdapter iniciar(String caminho) {
        conversor = new ConversorLog();
        adapter = new LogJSONAdapter(openFile(caminho + ".json"));
        return this;
    }
}
