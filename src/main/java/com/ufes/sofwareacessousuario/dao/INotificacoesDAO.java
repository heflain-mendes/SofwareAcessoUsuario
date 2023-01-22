/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.model.Notificacao;
import com.ufes.sofwareacessousuario.model.NotificacaoDTO;
import com.ufes.sofwareacessousuario.util.UsuarioRetorno;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface INotificacoesDAO {
    public void enviarNoticacao(Notificacao notification) throws Exception;
    //public int getQtdNotificacoes(UsuarioRetorno user) throws Exception;
    public List<NotificacaoDTO> getNotificacoes(UsuarioRetorno user) throws Exception;
    public void marcaComoLida(long id) throws Exception;
}
