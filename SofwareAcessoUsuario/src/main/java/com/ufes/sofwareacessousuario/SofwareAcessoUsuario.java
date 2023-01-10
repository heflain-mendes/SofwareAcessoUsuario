package com.ufes.sofwareacessousuario;

import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import java.util.regex.Pattern;
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
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possivel aplicar tema",
                    "informação sobre tema",
                    JOptionPane.INFORMATION_MESSAGE
               );
        }

        new PrincipalPresenter();   
    }
}
