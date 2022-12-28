/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 *
 * @author heflainrmendes
 */
public class FileConfigService {
    public static final String LOAD_FILE = "LOAD_FILE";
    public static final String UPDATE_TYPE_LOG = "UPDATE_TYPE_LOG";
    
    private static final String PATH_LOG = "pathLog";
    private static final String FORMAT_LOG = "fotmatLog";
    
    private static File file;
    private static Properties properties;
    private static EventManager listerners = new EventManager();

    public static void subscribe(EventListerners el) {
        listerners.subscribe(el);
    }

    private static void notify(String mensagem) {
        listerners.notify(mensagem);
    }

    public static void loadFile(String caminhoProperties) throws Exception {
        properties = new Properties();
        file = new File(
                caminhoProperties);
        
        try(FileReader f = new FileReader(file)){
            properties.load(f);
        }catch(Exception e){
            throw new Exception("Problema ao ler no arquivo de propriedade");
        }
        

        notify(LOAD_FILE);
    }

    public static String getPathLog() {
        return properties.getProperty(PATH_LOG);
    }

    public static String getTypeLog() {
        return properties.getProperty(FORMAT_LOG);
    }

    public static void setTypeLog(String typeLog) throws Exception {
        System.out.println(typeLog);
        properties.setProperty(FORMAT_LOG, typeLog);
        
        try(FileWriter f = new FileWriter(file);){
            properties.store(f, null);
        }catch(Exception e){
            throw new Exception("Problema ao escrever no arquivo de propriedade");
        }
        
        notify(UPDATE_TYPE_LOG);
    }
}
