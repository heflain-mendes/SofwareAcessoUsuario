/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.NotificationDTO;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.configuracao.FileConfigService;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAOProxy;
import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAOProxy;

/**
 * Gerencia o usuario logado, seus dados e notificaçoes
 *
 * @author heflainrmendes
 */
public class UsuarioLogadoService {

    public static String MARCADO_LIDO = "marcado como lido";
    public static String USUARIO_LOGADO = "user logged";
    public static String FALHA_LOGAR_USUARIO = "falha logar usuario";
    public static String USUARIO_DESLOGADO = "user unlogged";

    private IUserDAOProxy userDAO;
    private INotificationDAOProxy notificacoesDAO;
    private List<NotificationRetorno> notificacoes;
    private UserRetorno user;
    private EventManager eventManager;

    private static UsuarioLogadoService instance;

    private UsuarioLogadoService() {
        eventManager = new EventManager();

        String caminho = FileConfigService.getInstance().getConfiguracao(
                FileConfigService.CAMINHO_BD
        );

        String SGDB = FileConfigService.getInstance().getConfiguracao(
                FileConfigService.FORMATO_BD
        );

        IAbstractFactoryDAO fabrica = new ConfiguracaoBD().getFabrica(SGDB);
        try {
            notificacoesDAO = fabrica.criarNotificationDAO(caminho);
            notificacoes = new ArrayList<>();
            userDAO = fabrica.criarUserDAO(caminho);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static UsuarioLogadoService getInstance() {
        if (instance == null) {
            instance = new UsuarioLogadoService();
        }

        return instance;
    }

    public void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    public boolean userLogged() {
        return user != null;
    }

    public String getNome() {
        if (user != null) {
            return user.getName();
        }
        return null;
    }

    public long getId() {
        if (user != null) {
            return user.getId();
        }
        return -1;
    }

    public String getType() {
        if (user != null) {
            return user.getType();
        }
        return null;
    }

    public String getState() {
        if (user != null) {
            return user.getState();
        }
        return null;
    }

    public UserRetorno getUser() {
        if (user == null) {
            return null;
        }

        return new UserRetorno(user);
    }

    public void enviarNoticacao(UserRetorno user, String assunto, String mensagem) {
        if (!this.user.getType().equals(UserRetorno.ADMINISTERED)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getName()
                    + " não tem permissão enviar"
                    + " uma notificação para "
                    + user.getName()
                    + "\nO sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        try {
            notificacoesDAO.enviarNoticacao(new Notification(
                    getId(),
                    user.getId(),
                    assunto,
                    mensagem
            ));

            LogService.getInstance().escrever(new SystemLog(
                    LogService.ENVIO_NOTIFICAO,
                    user.getName(),
                    LocalDateTime.now(),
                    getNome()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void carregarListaNotificacoes() {
        if (this.user.getState().equals(UserRetorno.UNAUTORIZED)) {
            return;
        }

        try {
            List<NotificationDTO> ns = notificacoesDAO.getNotifications(user);

            for (var n : ns) {
                notificacoes.add(
                        new NotificationRetorno(
                                n.getId(),
                                UsuariosDAOService.getInstance().getUsuario(n.getIdRemetente()).getName(),
                                n.getAssunto(),
                                n.getMensagem(),
                                Notification.LIDO == n.getEstado() ? NotificationRetorno.LIDO : NotificationRetorno.NAO_LIDO
                        )
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public boolean login(String nome, String senha) {
        UserRetorno user = null;

        try {
            user = userDAO.fazerLogin(nome, senha);

            if (user != null) {
                this.user = user;
                eventManager.notify(USUARIO_LOGADO);
                if(user.getState().equals(UserRetorno.AUTORIZED)){
                    carregarListaNotificacoes();
                }
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            String mens = ex.getMessage();

            if (mens == null) {
                mens = "Ocorreu um erro inesperado";
            }

            JOptionPane.showMessageDialog(
                    null,
                    mens,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Para sua segurança o sistema sera encerado",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            System.exit(1);
        }

        return false;
    }

    public void logout() {
        if (user != null) {
            eventManager.notify(USUARIO_DESLOGADO);
            user = null;
            notificacoes.clear();
            
        }
    }

    public int getQtdNotificacoesNaoLida() {
        if (this.user.getState().equals(UserRetorno.UNAUTORIZED)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getName()
                    + " não possui permissão para saber a quantidade de"
                    + " notificações recebidas"
                    + "\nO sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        int i = 0;
        for (var n : notificacoes) {
            if (n.getEstado().equals(NotificationRetorno.NAO_LIDO)) {
                i++;
            }
        }

        return i;
    }

    public List<NotificationRetorno> getNotifications() {
        if (this.user.getState().equals(UserRetorno.UNAUTORIZED)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getName()
                    + " não possui permissão para obter as"
                    + " notificações recebidas"
                    + "\nO sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
        return Collections.unmodifiableList(notificacoes);
    }

    public void marcaComoLida(NotificationRetorno retorno) {
        if (this.user.getState().equals(UserRetorno.UNAUTORIZED)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getName()
                    + " não possui permissão para marcar notificaçoes como lida"
                    + "O sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        try {
            notificacoesDAO.marcaComoLida(retorno.getId());
            retorno.setEstado(NotificationRetorno.LIDO);
            eventManager.notify(MARCADO_LIDO);

            /**
             * nomeContato: usuario que leu a mensagem nomeUsuario: usuario que
             * enviu a mensagem
             */
            LogService.getInstance().escrever(new SystemLog(
                    LogService.LEITURA_NOTIFICACAO,
                    user.getName(),
                    LocalDateTime.now(),
                    retorno.getRemetente()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<String> atualizarSenha(String password) {
        if (this.user.getState().equals(UserRetorno.UNAUTORIZED)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getName()
                    + " não possui permissão para auterar senha"
                    + "O sistema será fechado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        List<String> recusasSenha = new VerificadorSenha().verificar(password);

        if (!recusasSenha.isEmpty()) {
            return recusasSenha;
        }

        try {
            userDAO.atualizarSenha(getUser(), password);
            LogService.getInstance().escrever(new SystemLog(
                    LogService.ALTERACAO_SENHA,
                    getNome(),
                    LocalDateTime.now(),
                    getNome()
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return recusasSenha;
    }
}
