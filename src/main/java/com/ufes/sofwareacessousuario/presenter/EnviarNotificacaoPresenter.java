/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter;

import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;
import com.ufes.sofwareacessousuario.util.IUsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import com.ufes.sofwareacessousuario.util.UsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.view.EnviarNotificacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class EnviarNotificacaoPresenter implements EventListerners{

    private UsuarioRetorno usuarioReceptor;
    private EnviarNotificacaoView view;
    private PrincipalPresenter principalPresenter;

    public EnviarNotificacaoPresenter(UsuarioRetorno usuario,PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
        this.usuarioReceptor = usuario;

        view = new EnviarNotificacaoView();

        view.getTxtDestinatario().setText(usuarioReceptor.getNome());
        view.getTxtDestinatario().setEnabled(false);

        view.getbtnEnviar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviar();
            }
        });
        
        UsuariosDAOServiceProxy.getInstance().subcribe(this);
        
        principalPresenter.addView(view);
        view.setVisible(true);
    }

    private void enviar() {
        UsuariosDAOServiceProxy.getInstance().enviarNoticacao(
                usuarioReceptor,
                view.getTxtAssunto().getText(),
                view.getTxtMessagem().getText()
        );
        
        JOptionPane.showMessageDialog(
                null,
                "Eviado com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
        
        UsuariosDAOServiceProxy.getInstance().unsubcribe(this);
        view.dispose();
        principalPresenter.removerView(view);
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(IUsuariosDAOServiceProxy.USUARIO_REMOVIDO)){
            UsuarioRetorno r = UsuariosDAOServiceProxy.getInstance().getUsuario(usuarioReceptor.getId());
            
            if(r == null){
                JOptionPane.showMessageDialog(
                        null,
                        "Usuario foi removido\n"
                                + "a tela será fechada",
                        "Usuario removido",
                        JOptionPane.INFORMATION_MESSAGE
                );
                
                UsuariosDAOServiceProxy.getInstance().unsubcribe(this);
                view.dispose();
                principalPresenter.removerView(view);
            }
        }
    }
}
