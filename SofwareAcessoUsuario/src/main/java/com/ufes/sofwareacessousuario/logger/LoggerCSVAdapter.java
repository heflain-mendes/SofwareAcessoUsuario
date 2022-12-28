/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogCSVAdapter;
import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.model.SystemLog;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class LoggerCSVAdapter extends LoggerMyProjectAdapter{
    LogCSVAdapter adapter;

    public LoggerCSVAdapter(String caminho) {
        adapter  = new LogCSVAdapter(openFile(caminho + ".csv"));
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
