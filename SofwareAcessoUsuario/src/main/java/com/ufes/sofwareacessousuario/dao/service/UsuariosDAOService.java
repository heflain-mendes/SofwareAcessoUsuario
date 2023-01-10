/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.service;

import com.ufes.sofwareacessousuario.dao.interfaces.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.configuracao.ArquivoDeCofiguracaoService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.model.Notificacao;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.validacaonome.ValidadorNome;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificacoesDAO;
import com.ufes.sofwareacessousuario.dao.interfaces.IUsuarioDAOProxy;
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
    private IUsuarioDAOProxy usuarioDAO;
    private INotificacoesDAO notificacoesDAO;
    private List<UsuarioRetorno> usuarios;
    private EventManager eventManager;
    private UsuarioLogadoService usuarioLogado;

    private UsuariosDAOService() {
        eventManager = new EventManager();
        usuarioLogado = UsuarioLogadoService.getInstance();
        String caminho = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.CAMINHO_BD
        );

        String SGDB = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATO_BD
        );

        IAbstractFactoryDAO fabrica = new ConfiguracaoBD().getFabrica(SGDB);
        try {
            usuarioDAO = fabrica.criarUserDAO(caminho);
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
            return usuarioDAO.possuiCadastrosDeUsuario();
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

    public List<UsuarioRetorno> getUsers() {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + "não tem permissão está para obter lista de usuarios"
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        return Collections.unmodifiableList(usuarios);
    }

    public VerificacoesRegistro registered(String name, String password) {
        if (usuarioLogado.userLogged()) {
            if (usuarioLogado.getType().equals(UsuarioRetorno.USUARIO)) {
                falhaDeSeguranca("O usuário "
                        + usuarioLogado.getNome()
                        + "não tem permissão está para obter lista de usuarios"
                        + "O sistema será encerado");
                System.exit(1);
            }
        }
        
        VerificacoesRegistro verificao = new VerificacoesRegistro(
                nomeEmUso(name),
                new ValidadorNome().validar(name),
                new VerificadorSenha().verificar(password)
        );
        
        if(verificao.possuiRecusas()){
            return verificao;
        }
        
        try {
            int state = definirEstado();
            int type = definirTipo();

            usuarioDAO.registrar(new UsuarioRegistro(name, password, state, type));
            UsuarioRetorno u = usuarioDAO.getUltimoUsuarioAdd();

            if (u == null) {
                throw new Exception("O usuário "
                        + name
                        + " aparentemente foi adicionado "
                        + "mas, não foi possivel reculpera-lo do banco de dados");
            } else {
                if(usuarios != null){
                    usuarios.add(u);
                }
            }

            eventManager.notify(USUARIO_ADICIONADO);

            u = usuarioLogado.getUser();
            LogService.getInstance().escrever(new SystemLog(
                    LogService.INCLUSAO,
                    name,
                    LocalDateTime.now(),
                    u == null ? "" : u.getNome()
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

        return verificao;
    }

    private int definirEstado() throws Exception {
        UsuarioLogadoService usuarioLogado = this.usuarioLogado;
        if (!possuiCadastrosDeUsuario()) {
            //primeiro registro
            return UsuarioRegistro.AUTORIZADO;
        }

        if (usuarioLogado.userLogged()) {
            if (usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
                //um adimistrado estive registrando
                return UsuarioRegistro.AUTORIZADO;
            }
        }

        return UsuarioRegistro.DESAUTORIZADO;

    }

    private int definirTipo() {
        if (!possuiCadastrosDeUsuario()) {
            return UsuarioRegistro.ADMINISTRADOR;
        }
        return UsuarioRegistro.USUARIO;
    }

    public void autorizarUsuario(UsuarioRetorno user) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        try {
            usuarioDAO.autorizarUsuario(user);
            user.setEstado(UsuarioRetorno.AUTORIZADO);

            LogService.getInstance().escrever(new SystemLog(
                    LogService.AUTORIZACAO_USUARIO,
                    user.getNome(),
                    LocalDateTime.now(),
                    usuarioLogado.getNome()
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

    public void removerUsuario(UsuarioRetorno user) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para remover usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        try {
            usuarioDAO.removerUsuario(user);
            usuarios.remove(user);
            eventManager.notify(USUARIO_REMOVIDO);
            LogService.getInstance().escrever(new SystemLog(
                    LogService.EXCLUSAO,
                    user.getNome(),
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

    public UsuarioRetorno getUsuario(long id) {
        for (var u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar o usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }

        try {
            notificacoesDAO.enviarNoticacao(new Notificacao(
                    usuarioLogado.getId(),
                    user.getId(),
                    assunto,
                    mensagem
            ));

            LogService.getInstance().escrever(new SystemLog(
                    LogService.ENVIO_NOTIFICAO,
                    user.getNome(),
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

    public boolean nomeEmUso(String nome){
        try {
            return usuarioDAO.nomeEmUso(nome);
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

    public void update(String mensagem) {
        if (UsuarioLogadoService.USUARIO_DESLOGADO.equals(mensagem)) {
            try {
                usuarios.clear();
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
                if (usuarioLogado.getState().equals(UsuarioRetorno.AUTORIZADO)) {
                    usuarios = usuarioDAO.getUsuarios();
                    var user = usuarioLogado.getUser();
                    for(var u : usuarios){
                        if(u.getId() == user.getId()){
                            usuarios.remove(u);
                            break;
                        }
                    }
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
