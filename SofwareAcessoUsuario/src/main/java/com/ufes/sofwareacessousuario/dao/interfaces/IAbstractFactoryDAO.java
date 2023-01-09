/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.interfaces;

/**
 *
 * @author Heflain
 */
public interface IAbstractFactoryDAO {
    public INotificationDAOProxy criarNotificationDAO(String caminho) throws Exception;
    public IUserDAOProxy criarUserDAO(String caminho) throws Exception;
    public String getTipoPersistencia();
}
