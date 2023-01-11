/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogJSONAdapter;
import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.util.ArquivoDeCofiguracaoService;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerJSONAdapter extends LoggerAdapter{
    private ConversorLog conversor;
    private LogJSONAdapter adapter;
    
    public LoggerJSONAdapter(String caminho) {
        conversor = new ConversorLog();
        adapter = new LogJSONAdapter(openFile(caminho + ".json"));
    }

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
}
