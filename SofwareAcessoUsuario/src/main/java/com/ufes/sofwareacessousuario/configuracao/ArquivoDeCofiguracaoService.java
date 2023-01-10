/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.configuracao;

import com.ufes.sofwareacessousuario.dao.service.UsuarioRetorno;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.presenter.principal.UsuarioLogado;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class ArquivoDeCofiguracaoService {

    public static final String ARQUIVO_CARREGADO = "LOAD_FILE";

    public static final String CAMINHO_LOG = "pathLog";
    public static final String FORMATOR_LOG = "fotmatLog";
    public static final String CAMINHO_BD = "pathBD";
    public static final String FORMATO_BD = "fotmatBD";

    private File file;
    private Properties properties;
    private EventManager listerners = new EventManager();

    private static ArquivoDeCofiguracaoService instance;

    private ArquivoDeCofiguracaoService() {
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

        listerners.notify(ARQUIVO_CARREGADO);
    }

    public static ArquivoDeCofiguracaoService getInstance() {
        if (instance == null) {
            instance = new ArquivoDeCofiguracaoService();
        }

        return instance;
    }

    public void subscribe(EventListerners el) {
        listerners.subscribe(el);
    }

    public void unsubscribe(EventListerners el) {
        listerners.unsubcribe(el);
    }
    
    public String getConfiguracao(String confifuracao){
        return properties.getProperty(confifuracao);
    }

    public void setFormatoLog(String typeLog) throws Exception {
        if(!UsuarioLogadoService.getInstance().getType().equals(UsuarioRetorno.ADMINISTRADOR)){
            return;
        }
        
        properties.setProperty(FORMATOR_LOG, typeLog);

        try (FileWriter f = new FileWriter(file);) {
            properties.store(f, null);
        } catch (Exception e) {
            throw new Exception("Problema ao escrever no arquivo de propriedade");
        }

        listerners.notify(FORMATOR_LOG);
    }
}
