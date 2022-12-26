/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.observable.fileConfigService.EventListerners;
import com.ufes.sofwareacessousuario.service.FileConfigService;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class LogService implements EventListerners {
    public static String INCLUSAO = "INCLUSAO";
    public static String ALTERACAO = "ALTERACAO";
    public static String EXCLUSAO = "EXCLUSAO";
    public static String ENVIO_NOTIFICAO = "ENVIO NOTIFICAO";
    public static String LEITURA_NOTIFICACAO = "LEITURA NOTIFICACAO";
    public static String ALTERACAO_SENHA = "ALTERACAO SENHA";
    public static String AUTORIZACAO_USUARIO = "AUTORIZACAO USUARIO";   
    
    private static LoggerAdapter loggerAdapter;
    private static final String[] opcoesLog = {"JSON", "CSV"};

    public static void setLoggerAdapter(LoggerAdapter loggerAdapter) throws IOException {
        if (loggerAdapter != null && !loggerAdapter.equals(LogService.loggerAdapter)) {
            if (LogService.loggerAdapter == null) {
                LogService.loggerAdapter = loggerAdapter;
            } else {
                loggerAdapter.escrever(LogService.loggerAdapter.exportaTodos().toArray(new Log[0]));
                LogService.loggerAdapter = loggerAdapter;
            }
        }
    }
    
    public static void escrever(Log... log) throws Exception{
        if(loggerAdapter == null){
            throw new Exception("LogService não foi configurada");
        }
        
        loggerAdapter.escrever(log);
    }
    
    public List<Log> exportaTodos() throws Exception{
        if(loggerAdapter == null){
            throw new Exception("LogService não foi configurada");
        }
        
        return loggerAdapter.exportaTodos();
    }
    
    public void configLog(String type) {
        try {
            if (type.equals(opcoesLog[0])) {
                LogService.setLoggerAdapter(new LoggerJSONAdapter(FileConfigService.getPathLog()));
            } else if (type.equals(opcoesLog[1])) {
                LogService.setLoggerAdapter(new LoggerCSVAdapter(FileConfigService.getPathLog()));
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "tipo log invalido",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void update(String mensagem, String info) {
        if (mensagem.equals(EventListerners.LOAD_FILE)
                || mensagem.equals(EventListerners.UPDATE_TYPE_LOG)) {
            configLog(info);
        }
    }
    
    public static String[] getOpcoesLog() {
        return opcoesLog;
    }
}
