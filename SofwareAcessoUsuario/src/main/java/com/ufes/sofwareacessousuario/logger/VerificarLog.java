/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
public class VerificarLog {
    private List<LoggerAdapter> logs;

    public VerificarLog(String caminho) {
        logs = new ArrayList();
        
        logs.add(new LoggerJSONAdapter(caminho));
        logs.add(new LoggerCSVAdapter(caminho)); 
    }

    public List<String> tiposLogs(){
        List<String> nomes = new ArrayList();
        
        for(var l : logs){
            nomes.add(l.getNome());
        }
        
        return nomes;
    }
    
    public LoggerAdapter getLog(String formatoLog){ 
        for(var l : logs){
            if(l.getLog(formatoLog)){
                return l;
            }
        }
        
        System.out.println("Retornou null");
        
        return null;
    }
}
