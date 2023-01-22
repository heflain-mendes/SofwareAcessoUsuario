/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.dao.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.dao.INotificacoesDAO;
import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;
import com.ufes.sofwareacessousuario.dao.ConfiguracaoBD;
import static com.ufes.sofwareacessousuario.util.IUsuarioLogadoServiceProxy.MARCADO_LIDO;
import static com.ufes.sofwareacessousuario.util.IUsuarioLogadoServiceProxy.USUARIO_DESLOGADO;
import static com.ufes.sofwareacessousuario.util.IUsuarioLogadoServiceProxy.USUARIO_LOGADO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.Notificacao;
import com.ufes.sofwareacessousuario.model.NotificacaoDTO;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Heflain
 */
class UsuarioLogadoServiceReal implements IUsuarioLogadoServiceProxy{
    private IUsuarioDAO userDAO;
    private INotificacoesDAO notificacoesDAO;
    private List<NotificacaoRetorno> notificacoes;
    private UsuarioRetorno user;
    private EventManager eventManager;

    UsuarioLogadoServiceReal() {
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

    @Override
    public void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    @Override
    public void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    @Override
    public boolean isLogado() {
        return user != null;
    }

    @Override
    public String getNome() {
        if (user != null) {
            return user.getNome();
        }
        return null;
    }

    @Override
    public long getId() {
        if (user != null) {
            return user.getId();
        }
        return -1;
    }

    @Override
    public String getType() {
        if (user != null) {
            return user.getTipo();
        }
        return null;
    }

    @Override
    public String getState() {
        if (user != null) {
            return user.getEstado();
        }
        return null;
    }

    @Override
    public UsuarioRetorno getUser() {
        if (user == null) {
            return null;
        }

        return new UsuarioRetorno(user);
    }

    @Override
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
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
                                UsuariosDAOServiceProxy.getInstance().getUsuario(n.getIdRemetente()).getNome(),
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

    @Override
    public boolean login(String nome, String senha) {
        UsuarioRetorno user = null;

        try {
            user = userDAO.fazerLogin(nome, senha);

            if (user != null) {
                this.user = user;
                if(user.getEstado().equals(UsuarioRetorno.AUTORIZADO)){
                    carregarListaNotificacoes();
                }
                eventManager.notify(USUARIO_LOGADO);
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

    @Override
    public void logout() {
        if (user != null) {
            eventManager.notify(USUARIO_DESLOGADO);
            user = null;
            notificacoes.clear();
            
        }
    }

    @Override
    public int getQtdNotificacoesNaoLida() {
        int i = 0;
        for(var n : notificacoes){
            if(n.getEstado().equals(NotificacaoRetorno.NAO_LIDO)){
                i++;
            }
        }
        
        return i;
    }

    @Override
    public List<NotificacaoRetorno> getNotificacoes() {
        return Collections.unmodifiableList(notificacoes);
    }

    @Override
    public void marcaComoLida(NotificacaoRetorno retorno) {
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

    @Override
    public List<String> atualizarSenha(String password) {
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
