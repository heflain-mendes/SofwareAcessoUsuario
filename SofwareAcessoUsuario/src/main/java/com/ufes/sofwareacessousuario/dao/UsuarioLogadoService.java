/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAO;
import com.ufes.sofwareacessousuario.dao.sqlite.NotificationSQLiteDAO;
import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.NotificationDTO;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author heflainrmendes
 */
public class UsuarioLogadoService {

    public static String LIDA = "marcada como lida";
    public static String USUARIO_LOGADO = "user logged";
    public static String USUARIO_DESLOGADO = "user unlogged";

    private INotificationDAO notificacoesDAO;
    private List<NotificationRetorno> notificacoes;
    private UserRetorno user;
    private EventManager eventManager;

    private static UsuarioLogadoService instance;

    private UsuarioLogadoService() {
        eventManager = new EventManager();
        try {
            notificacoesDAO = new NotificationSQLiteDAO();
            notificacoes = new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static UsuarioLogadoService getInstance() {
        if (instance == null) {
            instance = new UsuarioLogadoService();
        }

        return instance;
    }

    public void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    public boolean userLogged() {
        return user != null;
    }

    public String getNome() {
        return user.getName();
    }

    public long getId() {
        return user.getId();
    }

    public int getType() {
        return user.getType();
    }

    public int getState() {
        return user.getState();
    }

    public UserRetorno getUser() {
        return new UserRetorno(
                user.getId(),
                user.getName(),
                user.getState(),
                user.getType()
        );
    }

    public void login(UserRetorno user) {
        if (this.user == null) {
            this.user = user;
            carregarListaNotificacoes();
            eventManager.notify(USUARIO_LOGADO);
        }
    }

    private void carregarListaNotificacoes() {
        try {
            List<NotificationDTO> ns = notificacoesDAO.getNotifications(user);

            for (var n : ns) {
                notificacoes.add(
                        new NotificationRetorno(
                                n.getId(),
                                UsersDAOService.getInstance().getNomeUsuario(n.getIdRemetente()),
                                n.getAssunto(),
                                n.getMensagem(),
                                Notification.LIDO == n.getEstado() ? NotificationRetorno.LIDO : NotificationRetorno.NAO_LIDO
                        )
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void logout() {
        if (user != null) {
            user = null;
            notificacoes.clear();
            eventManager.notify(USUARIO_DESLOGADO);
        }
    }

    

    public int qtdNotifications() {
        return notificacoes.size();
    }

    public List<NotificationRetorno> getNotifications() {
        return Collections.unmodifiableList(notificacoes);
    }

    public void marcaComoLida(NotificationRetorno retorno) {
        try {
            notificacoesDAO.marcaComoLida(retorno.getId());
            retorno.setEstado(LIDA);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
//    private void adicionarNotificacao(NotificationDTO n) {
//        notificacoes.add(
//                new NotificationRetorno(
//                        n.getId(),
//                        UsersDAOService.getInstance().getNomeUsuario(n.getIdRemetente()),
//                        n.getAssunto(),
//                        n.getMensagem(),
//                        Notification.LIDO == n.getEstado() ? NotificationRetorno.LIDO : NotificationRetorno.NAO_LIDO
//                )
//        );
//    }
}
