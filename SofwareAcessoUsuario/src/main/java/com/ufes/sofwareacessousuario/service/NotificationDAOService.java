/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.service;

import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.SystemLog;
import com.ufes.sofwareacessousuario.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class NotificationDAOService {
    private static final List<Notification> notifications = new ArrayList();
    
    public static void enviarNoticacao(User user, String mensagem){
        
        LogService.escrever(new SystemLog(
                LogService.ENVIO_NOTIFICAO,
                user.getName(),
                LocalDateTime.now(),
                LoggedUserService.getNome()
        ));
        
        notifications.add(new Notification(
                notifications.size() + 1,
                LoggedUserService.getId(),
                user.getId(),
                mensagem
        ));
    }
}
