/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.principal;

/**
 *
 * @author heflainrmendes
 */
public abstract class PrincipalPresenterState {
    PrincipalPresenter presenter;

    public PrincipalPresenterState(PrincipalPresenter presenter) {
        this.presenter = presenter;
        presenter.setState(this);
    }
    
    public abstract void configurarSenha();
    public abstract void verNotificacoes();
    public abstract void addUsuario();
    public abstract void listarUsuario();
    public abstract void abrirConfiguracoes();
    public abstract void deslogar();
}
