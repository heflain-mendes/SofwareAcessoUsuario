/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.SystemLog;
import com.ufes.sofwareacessousuario.model.User;
import com.ufes.sofwareacessousuario.model.notificationRetorno;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class NotificationDAOService {

    public static String DECRWMENT_NOTIFICATION = "decrement notification";

    private static final List<Notification> notifications = new ArrayList();
    private static EventManager eventManager = new EventManager();

    public static void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public static void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    static {
        notifications.add(new Notification(
                notifications.size() + 1,
                UserLoggedService.getId(),
                1,
                "teste",
                "mensagem"
        ));
    }

    public static void enviarNoticacao(User user, String assunto, String mensagem) {

        LogService.escrever(new SystemLog(
                LogService.ENVIO_NOTIFICAO,
                user.getName(),
                LocalDateTime.now(),
                UserLoggedService.getNome()
        ));

        notifications.add(new Notification(
                notifications.size() + 1,
                UserLoggedService.getId(),
                user.getId(),
                assunto,
                mensagem
        ));
    }

    public static int qtdNotifications() {
        int qtd = 0;
        long id = UserLoggedService.getId();

        for (var n : notifications) {
            if (n.getId() == id && n.getEstado() == Notification.NAO_LIDO) {
                qtd++;
            }
        }

        return qtd;
    }

    public static List<notificationRetorno> getNotifications(long id) {
        List<notificationRetorno> list = new ArrayList();

        for (var n : notifications) {
            if (n.getIdReceptor() == id) {
                list.add(new notificationRetorno(
                        n.getId(),
                        UserDAOService.getNameUsuario(n.getIdRemetente()),
                        UserDAOService.getNameUsuario(n.getIdReceptor()),
                        n.getAssunto(),
                        n.getMensagem(),
                        n.getEstado() == Notification.LIDO ? "SIM" : "NÃO"
                ));
            }
        }

        return list;
    }

    public static void marcaComoLida(long id) {
        for (var n : notifications) {
            if (n.getId() == id && n.getEstado() == Notification.NAO_LIDO) {
                n.setEstado(Notification.LIDO);
                eventManager.notify(NotificationDAOService.DECRWMENT_NOTIFICATION);

            }
        }
    }
}
