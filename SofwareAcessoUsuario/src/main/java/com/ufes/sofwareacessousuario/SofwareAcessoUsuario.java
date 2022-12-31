package com.ufes.sofwareacessousuario;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.service.FileConfigService;
import static java.lang.System.exit;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author heflainrmendes
 */
public class SofwareAcessoUsuario {

    public static void main(String[] args) throws Exception {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        initialsetting();
        new PrincipalPresenter();
    }

    private static void initialsetting() {
        FileConfigService.subscribe(new LogService());
        try {
            FileConfigService.loadFile("./resources/project.properties");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            exit(1);
        }
    }
}
