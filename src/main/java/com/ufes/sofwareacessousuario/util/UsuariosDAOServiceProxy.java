/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.observable.EventListerners;
import java.util.List;
import javax.swing.JOptionPane;
import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.dao.IUsuarioDAO;

/**
 * gerencias as daos
 *
 * @author heflainrmendes
 */
public class UsuariosDAOServiceProxy implements EventListerners, IUsuariosDAOServiceProxy {

    private static UsuariosDAOServiceProxy instance;
    private IUsuarioDAO usuarioDAO;
    private UsuarioLogadoServiceProxy usuarioLogado;

    private IUsuariosDAOServiceProxy instanciaReal;

    private UsuariosDAOServiceProxy() {
        instanciaReal = new UsuariosDAOServiceReal();
        usuarioLogado = UsuarioLogadoServiceProxy.getInstance();
        usuarioLogado.subcribe(this);
    }

    public static UsuariosDAOServiceProxy getInstance() {
        if (instance == null) {
            instance = new UsuariosDAOServiceProxy();
        }

        return instance;
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
    public boolean possuiUsuariosCadastrados() {
        return instanciaReal.possuiUsuariosCadastrados();
    }

    @Override
    public List<UsuarioRetorno> getUsuarios() {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + "não tem permissão está para obter lista de usuarios"
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        return instanciaReal.getUsuarios();
    }

    @Override
    public VerificacoesRegistro RegistrarUsuario(String name, String password) {
        if (usuarioLogado.isLogado()) {
            if (usuarioLogado.getType().equals(UsuarioRetorno.USUARIO)) {
                falhaDeSeguranca("O usuário "
                        + usuarioLogado.getNome()
                        + "não tem permissão está para obter lista de usuarios"
                        + "O sistema será encerado");
                System.exit(1);
            }
        }

        return instanciaReal.RegistrarUsuario(name, password);
    }

    @Override
    public void autorizarUsuario(UsuarioRetorno user) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        instanciaReal.autorizarUsuario(user);
    }

    @Override
    public void removerUsuario(UsuarioRetorno user) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para remover usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }
        instanciaReal.removerUsuario(user);
    }

    @Override
    public UsuarioRetorno getUsuario(long id) {
        if (usuarioLogado.isLogado()) {
            if (usuarioLogado.getState().equals(UsuarioRetorno.DESAUTORIZADO)) {
                falhaDeSeguranca("O usuário "
                        + usuarioLogado.getNome()
                        + " não tem permissão para obter informaçãoes"
                        + " de outros usuáruios"
                        + "\nO sistema será encerado");
                System.exit(1);
            }
        } else {
            falhaDeSeguranca("Para obter informações de usuarios deve fazer login"
                    + "\nO sistema será encerado");
            System.exit(1);
        }

        return instanciaReal.getUsuario(id);
    }

    @Override
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem) {
        if (!usuarioLogado.getType().equals(UsuarioRetorno.ADMINISTRADOR)) {
            falhaDeSeguranca("O usuário "
                    + usuarioLogado.getNome()
                    + " não tem permissão para autorizar o usuario "
                    + user.getNome()
                    + "\nO sistema será encerado");
            System.exit(1);
        }

        instanciaReal.enviarNoticacao(user, assunto, mensagem);
    }

    @Override
    public boolean nomeEmUso(String nome) {
        return instanciaReal.nomeEmUso(nome);
    }
    
    @Override
    public void atualizarService(String mensagem){
        instanciaReal.atualizarService(mensagem);
    }

    public void update(String mensagem) {
        atualizarService(mensagem);
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
