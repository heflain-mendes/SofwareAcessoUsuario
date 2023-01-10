/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificacoesDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.IUsuarioDAOProxy;

/**
 *
 * @author Heflain
 */
public class SqliteFactoryDAO implements IAbstractFactoryDAO {

    @Override
    public INotificacoesDAO criarNotificationDAO(String caminho) throws Exception {
        return new NotificacaoSQLiteDAO(caminho);
    }

    @Override
    public IUsuarioDAOProxy criarUserDAO(String caminho) throws Exception {
        return new UsuarioSQLiteDAOProxy(caminho);
    }

    @Override
    public String getTipoPersistencia() {
        return "SQLite";
    }
}
