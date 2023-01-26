/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.logger;

import com.mycompany.adaptador.LogAdapter;
import com.mycompany.model.Log;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.util.ArquivoDeCofiguracaoService;
import java.io.File;
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

    private LogAdapter loggerAdapter;
    private File file;
    private static LogService instance;

    private LogService() {
        String nome = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATOR_LOG
        );
        String caminho = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.CAMINHO_LOG
        );

        this.file = new File(caminho);
        
        loggerAdapter = new ConfiguracaoLog(file).getLog(nome);

        ArquivoDeCofiguracaoService.getInstance().subscribe(this);
    }

    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }

        return instance;
    }

    public void escrever(Log... log) {
        try {
            if (loggerAdapter == null) {
                throw new Exception("LogService não foi configurada");
            }

            loggerAdapter.escrever(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String mensagem) {
        if (mensagem.equals(ArquivoDeCofiguracaoService.ARQUIVO_CARREGADO)
                || mensagem.equals(ArquivoDeCofiguracaoService.FORMATOR_LOG)) {

            String nome = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATOR_LOG
            );

            LogAdapter newAdapter = new ConfiguracaoLog(file).getLog(nome);

            try {
                newAdapter.escrever(
                        loggerAdapter.exportaTodos().toArray(new Log[0]));
                loggerAdapter = newAdapter;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "não foi possivel alterar o log",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public List<String> getOpcoesLog() {
        return new ConfiguracaoLog(file).getFormatosLog();
    }
}
