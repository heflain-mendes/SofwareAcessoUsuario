/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.service.FileConfigService;
import com.ufes.sofwareacessousuario.service.PrincipalViewService;
import com.ufes.sofwareacessousuario.view.ConfigurationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class ConfigurationPresenter {

    private ConfigurationView view;

    public ConfigurationPresenter() {
        view = new ConfigurationView();

        view.getCbFormatsLogger().addItem(LogService.getOpcoesLog()[0]);
        view.getCbFormatsLogger().addItem(LogService.getOpcoesLog()[1]);

        view.getCbFormatsLogger().setSelectedItem(FileConfigService.getTypeLog());

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
            FileConfigService.setTypeLog(
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
