/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces;

import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.NotificationDTO;
import com.ufes.sofwareacessousuario.dao.UserRetorno;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface INotificationDAO {
    public NotificationDTO enviarNoticacao(Notification notification) throws Exception;
    public int qtdNotifications(UserRetorno user) throws Exception;
    public List<NotificationDTO> getNotifications(UserRetorno user) throws Exception;
    public void marcaComoLida(long id) throws Exception;
}
