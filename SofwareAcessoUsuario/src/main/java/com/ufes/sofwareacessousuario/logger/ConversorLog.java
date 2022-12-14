/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.model.Log;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class ConversorLog{
    protected Log[] converterLog(SystemLog... log){
        List<Log> logs = new ArrayList();
        
        for(var l : log){
            logs.add(new Log(
                    l.getOperacao(),
                    l.getNomecontato(),
                    LocalDateTime.parse(l.getDataHora(), SystemLog.getFormatoDataHora()),
                    l.getNomeUsuario())
            );
        }
        
        return logs.toArray(new Log[0]);
    }
    
    protected List<SystemLog> converterLog(Log... log){
        List<SystemLog> logs = new ArrayList();
        
        for(var l : log){
            logs.add(new SystemLog(
                    l.getOperacao(),
                    l.getNomecontato(),
                    LocalDateTime.parse(l.getDataHora(), SystemLog.getFormatoDataHora()),
                    l.getNomeUsuario())
            );
        }
        
        return logs;
    }
}
