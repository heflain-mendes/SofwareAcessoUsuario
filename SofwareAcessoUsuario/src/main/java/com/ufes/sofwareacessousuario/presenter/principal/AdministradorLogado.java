/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.principal.command.AdicionarUsuario;
import com.ufes.sofwareacessousuario.presenter.principal.command.ConfigurarSenha;
import com.ufes.sofwareacessousuario.presenter.principal.command.AbrirConfiguracoes;
import com.ufes.sofwareacessousuario.presenter.principal.command.ListarUsuarios;
import com.ufes.sofwareacessousuario.presenter.principal.command.Deslogar;
import com.ufes.sofwareacessousuario.presenter.principal.command.AtualizarNumeroDeNotificacoes;
import com.ufes.sofwareacessousuario.presenter.principal.command.VerNotificacoes;

/**
 *
 * @author Heflain
 */
public class AdministradorLogado extends PrincipalPresenterState implements EventListerners{

    public AdministradorLogado(PrincipalPresenter presenter) {
        super(presenter);
        
        presenter.view.getBtnAlterarSenha().setEnabled(true);
        presenter.view.getBtnVerNotificacoes().setEnabled(true);
        presenter.view.getBtnAlterarSenha().setVisible(true);
        presenter.view.getBtnVerNotificacoes().setVisible(true);
        
        presenter.view.getBtnAdiministrador().setVisible(true);
        presenter.view.getBtnAdiministrador().setEnabled(true);
        
        presenter.view.getBtnUsuario().setVisible(true);
        presenter.view.getBtnUsuario().setEnabled(true);
        
        UsuarioLogadoService.getInstance().subcribe(this);
    }

    @Override
    public void configurarSenha() {
        new ConfigurarSenha().executar();
    }

    @Override
    public void verNotificacoes() {
        new VerNotificacoes().executar();
    }

    @Override
    public void addUsuario() {
        new AdicionarUsuario().executar();
    }

    @Override
    public void listarUsuario() {
        new ListarUsuarios().executar();
    }

    @Override
    public void abrirConfiguracoes() {
        new AbrirConfiguracoes().executar();
    }

    @Override
    public void deslogar() {
        new Deslogar(presenter, presenter.view).executar();
        UsuarioLogadoService.getInstance().unsubcribe(this);
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoService.MARCADO_LIDO)){
            new AtualizarNumeroDeNotificacoes(presenter.view).executar();
        }
    }
}
