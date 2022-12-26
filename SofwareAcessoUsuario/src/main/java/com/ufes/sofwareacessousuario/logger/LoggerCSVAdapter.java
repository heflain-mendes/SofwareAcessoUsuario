/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogCSVAdapter;
import com.mycompany.model.Log;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerCSVAdapter extends LoggerAdapter{
    LogCSVAdapter adapter;

    public LoggerCSVAdapter(String caminho) {
        adapter  = new LogCSVAdapter(openFile(caminho + ".csv"));
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
