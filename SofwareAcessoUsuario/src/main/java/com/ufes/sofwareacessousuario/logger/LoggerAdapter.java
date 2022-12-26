/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.model.Log;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public abstract class LoggerAdapter {
    protected File openFile(String caminho){
        File file = new File(caminho);
        
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null, 
                        "não foi possivel criar arquivo de log",
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
        return file;
    }
    public abstract void escrever(Log... log) throws IOException;
    public abstract List<Log> exportaTodos() throws IOException;
}
