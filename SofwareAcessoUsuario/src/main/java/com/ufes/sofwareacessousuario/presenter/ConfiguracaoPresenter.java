/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.configuracao.ArquivoDeCofiguracaoService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.ConfiguracaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class ConfiguracaoPresenter {

    private ConfiguracaoView view;

    public ConfiguracaoPresenter() {
        view = new ConfiguracaoView();

        List<String> opc = LogService.getInstance().getOpcoesLog();
        
        for(var s : opc){
            view.getCbFormatsLogger().addItem(s);
        }

        view.getCbFormatsLogger().setSelectedItem(ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATOR_LOG
        ));

        view.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void salvar() {
        try {
            ArquivoDeCofiguracaoService.getInstance().setFormatoLog(
                    view.getCbFormatsLogger().getSelectedItem().toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        }
        view.dispose();
    }
}
