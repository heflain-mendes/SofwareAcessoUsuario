/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAOProxy;
import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAOProxy;

/**
 *
 * @author Heflain
 */
public class SqliteFactoryDAO implements IAbstractFactoryDAO {

    @Override
    public INotificationDAOProxy criarNotificationDAO(String caminho) throws Exception {
        return new NotificationSQLiteDAO(caminho);
    }

    @Override
    public IUserDAOProxy criarUserDAO(String caminho) throws Exception {
        return new UserSQLiteDAOProxy(caminho);
    }

    @Override
    public String getTipoPersistencia() {
        return "SQLite";
    }
}
