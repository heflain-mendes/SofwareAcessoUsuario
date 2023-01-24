/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface IUsuarioLogadoServiceProxy{
    public static String MARCADO_LIDO = "marcado como lido";
    public static String USUARIO_LOGADO = "user logged";
    public static String FALHA_LOGAR_USUARIO = "falha logar usuario";
    public static String USUARIO_DESLOGADO = "user unlogged";
    
    public void subcribe(EventListerners listerners);
    public void unsubcribe(EventListerners listerners);
    public boolean isLogado();
    public String getNome();
    public long getId();
    public String getType();
    public String getState();
    public UsuarioRetorno getUser();
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem);
    public boolean login(String nome, String senha);
    public void logout();
    public int getQtdNotificacoesNaoLida();
    public List<NotificacaoRetorno> getNotificacoes();
    public void marcaComoLida(NotificacaoRetorno retorno);
    public List<String> atualizarSenha(String password);
}
