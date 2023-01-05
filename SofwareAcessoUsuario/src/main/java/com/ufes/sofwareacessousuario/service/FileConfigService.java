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
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class FileConfigService {

    public static final String LOAD_FILE = "LOAD_FILE";
    public static final String UPDATE_TYPE_LOG = "UPDATE_TYPE_LOG";

    private static final String PATH_LOG = "pathLog";
    private static final String FORMAT_LOG = "fotmatLog";
    private static final String PATH_BD = "pathBD";

    private File file;
    private Properties properties;
    private EventManager listerners = new EventManager();

    private static FileConfigService instance;

    private FileConfigService() {
        properties = new Properties();
        file = new File(
                "./resources/project.properties");

        try (FileReader f = new FileReader(file)) {
            properties.load(f);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Problema ao ler no arquivo de propriedade, o sistema ira se"
                    + " fechado apos a confirmação",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        notify(LOAD_FILE);
    }

    public static FileConfigService getInstance() {
        if (instance == null) {
            instance = new FileConfigService();
        }

        return instance;
    }

    public void subscribe(EventListerners el) {
        listerners.subscribe(el);
    }

    public void unsubscribe(EventListerners el) {
        listerners.unsubcribe(el);
    }

    private void notify(String mensagem) {
        listerners.notify(mensagem);
    }

    public String getPathLog() {
        return properties.getProperty(PATH_LOG);
    }

    public String getPathBD() {
        return properties.getProperty(PATH_BD);
    }

    public String getTypeLog() {
        return properties.getProperty(FORMAT_LOG);
    }

    public synchronized void setTypeLog(String typeLog) throws Exception {
        System.out.println(typeLog);
        properties.setProperty(FORMAT_LOG, typeLog);

        try (FileWriter f = new FileWriter(file);) {
            properties.store(f, null);
        } catch (Exception e) {
            throw new Exception("Problema ao escrever no arquivo de propriedade");
        }

        notify(UPDATE_TYPE_LOG);
    }
}
