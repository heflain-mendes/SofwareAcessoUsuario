/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Gerencia o usuario logado, seus dados e notificaçoes
 *
 * @author heflainrmendes
 */
public class UsuarioLogadoServiceProxy implements IUsuarioLogadoServiceProxy {

    private static UsuarioLogadoServiceProxy instancia;
    private IUsuarioLogadoServiceProxy instanciaReal;

    private UsuarioLogadoServiceProxy() {
        instanciaReal = new UsuarioLogadoServiceReal();
    }

    public static UsuarioLogadoServiceProxy getInstance() {
        if (instancia == null) {
            instancia = new UsuarioLogadoServiceProxy();
        }

        return instancia;
    }

    @Override
    public void subcribe(EventListerners listerners) {
        instanciaReal.subcribe(listerners);
    }

    @Override
    public void unsubcribe(EventListerners listerners) {
        instanciaReal.unsubcribe(listerners);
    }

    @Override
    public boolean isLogado() {
        return instanciaReal.isLogado();
    }

    @Override
    public String getNome() {
        return instanciaReal.getNome();
    }

    @Override
    public long getId() {
        return instanciaReal.getId();
    }

    @Override
    public String getType() {
        return instanciaReal.getType();
    }

    @Override
    public String getState() {
        return instanciaReal.getState();
    }

    @Override
    public UsuarioRetorno getUser() {
        return instanciaReal.getUser();
    }

    @Override
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
        if (!getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            exibirMensagemErro(
                    "O usuário "
                    + getNome()
                    + " não tem permissão enviar"
                    + " uma notificação para "
                    + user.getNome()
                    + "\nO sistema será encerrado"
            );
            System.exit(1);
        }

        instanciaReal.enviarNoticacao(user, assunto, mensagem);
    }

    @Override
    public boolean login(String nome, String senha) {
        return instanciaReal.login(nome, senha);
    }

    @Override
    public void logout() {
        instanciaReal.logout();
    }

    @Override
    public int getQtdNotificacoesNaoLida() {
        return instanciaReal.getQtdNotificacoesNaoLida();
    }

    @Override
    public List<NotificacaoRetorno> getNotificacoes() {
        if (getState().equals(UsuarioRetorno.DESAUTORIZADO)) {
            exibirMensagemErro(
                    "O usuário "
                    + getNome()
                    + " não possui permissão para obter as"
                    + " notificações recebidas"
                    + "\nO sistema será encerrado"
            );
            System.exit(1);
        }
        
        return instanciaReal.getNotificacoes();
    }

    @Override
    public void marcaComoLida(NotificacaoRetorno retorno) {
        if (getState().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + getNome()
                    + " não possui permissão para marcar notificaçoes como lida"
                    + "O sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
        
        instanciaReal.marcaComoLida(retorno);
    }

    @Override
    public List<String> atualizarSenha(String password) {
        if (getState().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + getNome()
                    + " não possui permissão para auterar senha"
                    + "O sistema será fechado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
        
        return instanciaReal.atualizarSenha(password);
    }

    private void exibirMensagemErro(String msg) {
        JOptionPane.showMessageDialog(
                null,
                msg,
                "Falha de segurança",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
