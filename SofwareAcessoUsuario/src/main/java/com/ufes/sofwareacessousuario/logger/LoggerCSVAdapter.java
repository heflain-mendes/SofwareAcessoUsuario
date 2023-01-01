/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogCSVAdapter;
import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.model.SystemLog;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerCSVAdapter extends LoggerAdapter {

    private LogCSVAdapter adapter;
    private LoggerConversorMyProjectAdapter conversor;

    public LoggerCSVAdapter(String caminho) {
        conversor = new LoggerConversorMyProjectAdapter();
        adapter = new LogCSVAdapter(openFile(caminho + ".csv"));
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
