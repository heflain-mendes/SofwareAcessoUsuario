/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogAdapter;
import com.mycompany.adaptador.LogCSVAdapter;
import com.mycompany.adaptador.LogJSONAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
class ConfiguracaoLog {
    private List<LogAdapter> logs;
    
    ConfiguracaoLog(File file) {
        logs = new ArrayList();
        
        logs.add(new LogJSONAdapter(file));
        logs.add(new LogCSVAdapter(file)); 
    }

    List<String> getFormatosLog(){
        List<String> formatos = new ArrayList();
        
        for(var l : logs){
            formatos.add(l.toString());
        }
        
        return formatos;
    }
    
    LogAdapter getLog(String formatoLog){ 
        for(var l : logs){
            if(l.toString().equals(formatoLog)){
                return l;
            }
        }
        
        return null;
    }
}
