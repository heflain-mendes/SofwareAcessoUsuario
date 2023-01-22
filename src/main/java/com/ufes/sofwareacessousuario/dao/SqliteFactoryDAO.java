/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.dao.NotificacaoSQLiteDAO;
import com.ufes.sofwareacessousuario.dao.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.dao.INotificacoesDAO;
import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;

/**
 *
 * @author Heflain
 */
class SqliteFactoryDAO implements IAbstractFactoryDAO {

    @Override
    public INotificacoesDAO criarNotificationDAO(String caminho) throws Exception {
        return new NotificacaoSQLiteDAO(caminho);
    }

    @Override
    public IUsuarioDAO criarUserDAO(String caminho) throws Exception {
        return new UsuarioSQLiteDAO(caminho);
    }

    @Override
    public String getTipoPersistencia() {
        return "SQLite";
    }
}
