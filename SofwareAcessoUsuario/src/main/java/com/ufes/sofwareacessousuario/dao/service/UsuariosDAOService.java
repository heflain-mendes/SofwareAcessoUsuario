/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.UserRegistro;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.configuracao.FileConfigService;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAOProxy;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.dao.interfaces.IUserDAOProxy;
import com.ufes.sofwareacessousuario.model.Notification;

/**
 * gerencias as daos
 *
 * @author heflainrmendes
 */
public class UsuariosDAOService implements EventListerners {

    public static final String USUARIO_ADICIONADO = "usuário adiciondo";
    public static final String USUARIO_REMOVIDO = "usuario removido";
    public static final String USUARIO_AUTORIZADO = "usuario autorizado";

    private static UsuariosDAOService instance;
    private IUserDAOProxy userDAO;
    private INotificationDAOProxy notificacoesDAO;
    private List<UserRetorno> users;
    private EventManager eventManager;
    private UsuarioLogadoService usuarioLogado;

    private UsuariosDAOService() {
        eventManager = new EventManager();
        usuarioLogado = UsuarioLogadoService.getInstance();
        String caminho = FileConfigService.getInstance().getConfiguracao(
                FileConfigService.CAMINHO_BD
        );

        String SGDB = FileConfigService.getInstance().getConfiguracao(
                FileConfigService.FORMATO_BD
        );

        IAbstractFactoryDAO fabrica = new ConfiguracaoBD().getFabrica(SGDB);
        try {
            userDAO = fabrica.criarUserDAO(caminho);
            notificacoesDAO = fabrica.criarNotificationDAO(caminho);
            usuarioLogado.subcribe(this);
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

    public static UsuariosDAOService getInstance() {
        if (instance == null) {
            instance = new UsuariosDAOService();
        }

        return instance;
    }

    public void subcribe(EventListerners listerners) {
        eventManager.subscribe(listerners);
    }

    public void unsubcribe(EventListerners listerners) {
        eventManager.unsubcribe(listerners);
    }

    public boolean possuiCadastrosDeUsuario() {
        try {
            return userDAO.possuiCadastrosDeUsuario();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return false;
    }

    public List<UserRetorno> getUsers() {
        if (!usuarioLogado.getType().equals(UserRetorno.ADMINISTERED)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + "não tem permissão está para obter lista de usuarios"
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        return Collections.unmodifiableList(users);
    }

    public VerificacoesRegistro registered(String name, String password) {
        if (usuarioLogado.userLogged()) {
            if (usuarioLogado.getType().equals(UserRetorno.USER)) {
                falhaDeSeguranca("O usuário "
                        + usuarioLogado.getNome()
                        + "não tem permissão está para obter lista de usuarios"
                        + "O sistema será encerado");
                System.exit(1);
            }
        }
        VerificacoesRegistro verificacoes = null;
        try {
            int state = definirEstado();
            int type = definirTipo();
            verificacoes = userDAO.registrar(new UserRegistro(name, password, state, type));

            if (verificacoes.possuiRecusas()) {
                return verificacoes;
            }

            UserRetorno u = userDAO.getUltimoUsuarioAdd();

            if (u == null) {
                throw new Exception("O usuário "
                        + name
                        + " aparentemente foi adicionado "
                        + "mas, não foi possivel reculpera-lo do banco de dados");
            } else {
                users.add(u);
            }

            eventManager.notify(USUARIO_ADICIONADO);

            u = UsuarioLogadoService.getInstance().getUser();
            LogService.getInstance().escrever(new SystemLog(
                    LogService.INCLUSAO,
                    name,
                    LocalDateTime.now(),
                    u == null ? "" : u.getName()
            ));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return verificacoes;
    }

    private int definirEstado() throws Exception {
        UsuarioLogadoService usuarioLogado = UsuarioLogadoService.getInstance();
        if (!possuiCadastrosDeUsuario()) {
            //primeiro registro
            return UserRegistro.AUTORIZED;
        }

        if (usuarioLogado.userLogged()) {
            if (usuarioLogado.getType().equals(UserRetorno.ADMINISTERED)) {
                //um adimistrado estive registrando
                return UserRegistro.AUTORIZED;
            }
        }

        return UserRegistro.UNAUTORIZED;

    }

    private int definirTipo() {
        if (!possuiCadastrosDeUsuario()) {
            return UserRegistro.ADMINISTERED;
        }
        return UserRegistro.USER;
    }

    public boolean nomeEmUso(String nome) {
        try {
            return userDAO.nomeEmUso(nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return true;
    }

    public void autorizarUsuario(UserRetorno user) {
        if (!usuarioLogado.getType().equals(UserRetorno.ADMINISTERED)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar usuario "
                    + user.getName()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        try {
            userDAO.autorizarUsuario(user);
            user.setState(UserRetorno.AUTORIZED);

            LogService.getInstance().escrever(new SystemLog(
                    LogService.AUTORIZACAO_USUARIO,
                    user.getName(),
                    LocalDateTime.now(),
                    UsuarioLogadoService.getInstance().getNome()
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void removerUsuario(UserRetorno user) {
        if (!usuarioLogado.getType().equals(UserRetorno.ADMINISTERED)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para remover usuario "
                    + user.getName()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        try {
            userDAO.removerUsuario(user);
            users.remove(user);
            eventManager.notify(USUARIO_REMOVIDO);
            LogService.getInstance().escrever(new SystemLog(
                    LogService.EXCLUSAO,
                    user.getName(),
                    LocalDateTime.now(),
                    UsuarioLogadoService.getInstance().getNome()
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

    public UserRetorno getUsuario(long id) {
        for (var u : users) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public void enviarNoticacao(UserRetorno user, String assunto, String mensagem) {
        if (!usuarioLogado.getType().equals(UserRetorno.ADMINISTERED)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar o usuario "
                    + user.getName()
                    + "\nO sistema será encerado");
            System.exit(1);
        }

        try {
            notificacoesDAO.enviarNoticacao(new Notification(
                    usuarioLogado.getId(),
                    user.getId(),
                    assunto,
                    mensagem
            ));

            LogService.getInstance().escrever(new SystemLog(
                    LogService.ENVIO_NOTIFICAO,
                    user.getName(),
                    LocalDateTime.now(),
                    usuarioLogado.getNome()
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

    public void update(String mensagem) {
        if (UsuarioLogadoService.USUARIO_DESLOGADO.equals(mensagem)) {
            try {
                users.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (mensagem.equals(UsuarioLogadoService.USUARIO_LOGADO)) {
            try {
                if (usuarioLogado.getState().equals(UserRetorno.AUTORIZED)) {
                    users = userDAO.getUsuarios();
                    users.remove(usuarioLogado.getUser()); 
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
            users.remove(usuarioLogado.getUser());
        }
    }

    private void falhaDeSeguranca(String mensagem) {
        JOptionPane.showMessageDialog(
                null,
                mensagem,
                "Falha de segurança",
                JOptionPane.ERROR_MESSAGE
        );
        System.exit(1);
    }
}
