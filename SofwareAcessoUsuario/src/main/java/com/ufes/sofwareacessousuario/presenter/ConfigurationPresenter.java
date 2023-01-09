/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.configuracao.FileConfigService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.ConfigurationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class ConfigurationPresenter {

    private ConfigurationView view;

    public ConfigurationPresenter() {
        view = new ConfigurationView();

        List<String> opc = LogService.getInstance().getOpcoesLog();
        
        for(var s : opc){
            view.getCbFormatsLogger().addItem(s);
        }

        view.getCbFormatsLogger().setSelectedItem(FileConfigService.getInstance().getConfiguracao(
                FileConfigService.FORMATOR_LOG
        ));

        view.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        PrincipalViewService.add(view);
        view.setVisible(true);
    }

    private void save() {
        try {
            FileConfigService.getInstance().setFormatoLog(
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
