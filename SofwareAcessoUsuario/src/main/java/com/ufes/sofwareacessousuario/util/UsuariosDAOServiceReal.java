/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.util.ArquivoDeCofiguracaoService;
import com.ufes.sofwareacessousuario.dao.IAbstractFactoryDAO;
import com.ufes.sofwareacessousuario.dao.INotificacoesDAO;
import com.ufes.sofwareacessousuario.logger.LogService;
import com.ufes.sofwareacessousuario.logger.SystemLog;
import com.ufes.sofwareacessousuario.model.Notificacao;
import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.observable.EventManager;
import com.ufes.sofwareacessousuario.validacaonome.ValidadorNome;
import com.ufes.sofwareacessousuario.validacaosenha.VerificadorSenha;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;
import com.ufes.sofwareacessousuario.dao.ConfiguracaoBD;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heflain
 */
class UsuariosDAOServiceReal implements IUsuariosDAOServiceProxy {
    private IUsuarioDAO usuarioDAO;
    private INotificacoesDAO notificacoesDAO;
    private List<UsuarioRetorno> usuarios;
    private EventManager eventManager;
    private UsuarioLogadoServiceProxy usuarioLogado;

    UsuariosDAOServiceReal() {
        eventManager = new EventManager();
        usuarioLogado = UsuarioLogadoServiceProxy.getInstance();
        String caminho = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.CAMINHO_BD
        );

        String SGDB = ArquivoDeCofiguracaoService.getInstance().getConfiguracao(ArquivoDeCofiguracaoService.FORMATO_BD
        );

        IAbstractFactoryDAO fabrica = new ConfiguracaoBD().getFabrica(SGDB);
        try {
            usuarioDAO = fabrica.criarUserDAO(caminho);
            usuarios = usuarioDAO.getUsuarios();
            notificacoesDAO = fabrica.criarNotificationDAO(caminho);
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
    public boolean possuiUsuariosCadastrados() {
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
        
        return true;
    }

    @Override
    public List<UsuarioRetorno> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    @Override
    public VerificacoesRegistro RegistrarUsuario(String name, String password) {
        VerificacoesRegistro verificao = new VerificacoesRegistro(
                nomeEmUso(name),
                new ValidadorNome().validar(name),
                new VerificadorSenha().verificar(password)
        );

        if (verificao.possuiRecusas()) {
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
                if (usuarios != null) {
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
        UsuarioLogadoServiceProxy usuarioLogado = this.usuarioLogado;
        if (!possuiUsuariosCadastrados()) {
            //primeiro registro
            return UsuarioRegistro.AUTORIZADO;
        }

        if (usuarioLogado.isLogado()) {
            if (usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
                //um adimistrado estive registrando
                return UsuarioRegistro.AUTORIZADO;
            }
        }

        return UsuarioRegistro.DESAUTORIZADO;

    }

    private int definirTipo() {
        if (!possuiUsuariosCadastrados()) {
            return UsuarioRegistro.ADMINISTRADOR;
        }
        return UsuarioRegistro.USUARIO;
    }

    @Override
    public void autorizarUsuario(UsuarioRetorno user) {
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

    @Override
    public void removerUsuario(UsuarioRetorno user) {
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

    @Override
    public UsuarioRetorno getUsuario(long id) {
        for (var u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    @Override
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
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

    @Override
    public boolean nomeEmUso(String nome) {
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

    @Override
    public void update(String mensagem) {
        if (UsuarioLogadoServiceProxy.USUARIO_DESLOGADO.equals(mensagem)) {
            usuarios.add(usuarioLogado.getUser());
        } else if (mensagem.equals(UsuarioLogadoServiceProxy.USUARIO_LOGADO)) {
            var user = usuarioLogado.getUser();
            for (var u : usuarios) {
                if (u.getId() == user.getId()) {
                    usuarios.remove(u);
                    break;
                }
            };
        }
    }
}
