/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.Notificacao;
import com.ufes.sofwareacessousuario.model.NotificacaoDTO;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.configuracao.ArquivoDeCofiguracaoService;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificacoesDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.IUsuarioDAOProxy;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private IUsuarioDAOProxy userDAO;
    private INotificacoesDAO notificacoesDAO;
    private List<NotificacaoRetorno> notificacoes;
    private UsuarioRetorno user;
    private EventManager eventManager;

    private static UsuarioLogadoService instance;

    private UsuarioLogadoService() {
        eventManager = new EventManager();

        String caminho = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.CAMINHO_BD
        );

        String SGDB = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATO_BD
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
            return user.getNome();
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
            return user.getTipo();
        }
        return null;
    }

    public String getState() {
        if (user != null) {
            return user.getEstado();
        }
        return null;
    }

    public UsuarioRetorno getUser() {
        if (user == null) {
            return null;
        }

        return new UsuarioRetorno(user);
    }

    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
        if (!this.user.getTipo().equals(UsuarioRetorno.ADMINISTRADOR)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getNome()
                    + " não tem permissão enviar"
                    + " uma notificação para "
                    + user.getNome()
                    + "\nO sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        try {
            notificacoesDAO.enviarNoticacao(new Notificacao(
                    getId(),
                    user.getId(),
                    assunto,
                    mensagem
            ));

            LogService.getInstance().escrever(new SystemLog(
                    LogService.ENVIO_NOTIFICAO,
                    user.getNome(),
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
        if (this.user.getEstado().equals(UsuarioRetorno.DESAUTORIZADO)) {
            return;
        }

        try {
            List<NotificacaoDTO> ns = notificacoesDAO.getNotificacoes(user);

            for (var n : ns) {
                notificacoes.add(new NotificacaoRetorno(
                                n.getId(),
                                UsuariosDAOService.getInstance().getUsuario(n.getIdRemetente()).getNome(),
                                n.getAssunto(),
                                n.getMensagem(),
                                Notificacao.LIDO == n.getEstado() ? NotificacaoRetorno.LIDO : NotificacaoRetorno.NAO_LIDO
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
        UsuarioRetorno user = null;

        try {
            user = userDAO.fazerLogin(nome, senha);

            if (user != null) {
                this.user = user;
                eventManager.notify(USUARIO_LOGADO);
                if(user.getEstado().equals(UsuarioRetorno.AUTORIZADO)){
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
        if (this.user.getEstado().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getNome()
                    + " não possui permissão para saber a quantidade de"
                    + " notificações recebidas"
                    + "\nO sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        try {
            return notificacoesDAO.getQtdNotificacoes(this.user);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        return 0;
    }

    public List<NotificacaoRetorno> getNotifications() {
        if (this.user.getEstado().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getNome()
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

    public void marcaComoLida(NotificacaoRetorno retorno) {
        if (this.user.getEstado().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getNome()
                    + " não possui permissão para marcar notificaçoes como lida"
                    + "O sistema será encerrado",
                    "Falha de segurança",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        try {
            notificacoesDAO.marcaComoLida(retorno.getId());
            retorno.setEstado(NotificacaoRetorno.LIDO);
            eventManager.notify(MARCADO_LIDO);

            /**
             * nomeContato: usuario que leu a mensagem nomeUsuario: usuario que
             * enviu a mensagem
             */
            LogService.getInstance().escrever(new SystemLog(
                    LogService.LEITURA_NOTIFICACAO,
                    user.getNome(),
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
        if (this.user.getEstado().equals(UsuarioRetorno.DESAUTORIZADO)) {
            JOptionPane.showMessageDialog(
                    null,
                    "O usuário "
                    + this.user.getNome()
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
