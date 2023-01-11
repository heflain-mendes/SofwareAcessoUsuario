/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.util;

import com.ufes.sofwareacessousuario.model.VerificacoesRegistro;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import java.util.List;

/**
 *
 * @author Heflain
 */
public interface IUsuariosDAOServiceProxy {
    public static final String USUARIO_ADICIONADO = "usuário adiciondo";
    public static final String USUARIO_REMOVIDO = "usuario removido";
    public static final String USUARIO_AUTORIZADO = "usuario autorizado";
    
    public void subcribe(EventListerners listerners);
    public void unsubcribe(EventListerners listerners);
    public boolean possuiUsuariosCadastrados();
    public List<UsuarioRetorno> getUsuarios();
    public VerificacoesRegistro RegistrarUsuario(String name, String password);
    public void autorizarUsuario(UsuarioRetorno user);
    public void removerUsuario(UsuarioRetorno user);
    public UsuarioRetorno getUsuario(long id);
    public void enviarNoticacao(UsuarioRetorno user, String assunto, String mensagem);
    public boolean nomeEmUso(String nome);
    public void update(String mensagem);
}
