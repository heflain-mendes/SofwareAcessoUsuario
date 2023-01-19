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
class ConfiguracaoLog {
    private List<LoggerAdapter> logs;
    private String caminho;
    
    ConfiguracaoLog(String caminho) {
        this.caminho = caminho;
        logs = new ArrayList();
        
        logs.add(new LoggerJSONAdapter());
        logs.add(new LoggerCSVAdapter()); 
    }

    List<String> getTiposLogs(){
        List<String> nomes = new ArrayList();
        
        for(var l : logs){
            nomes.add(l.getNome());
        }
        
        return nomes;
    }
    
    LoggerAdapter getLog(String formatoLog){ 
        for(var l : logs){
            if(l.getLog(formatoLog)){
                return l.iniciar(caminho);
            }
        }
        
        return null;
    }
}
