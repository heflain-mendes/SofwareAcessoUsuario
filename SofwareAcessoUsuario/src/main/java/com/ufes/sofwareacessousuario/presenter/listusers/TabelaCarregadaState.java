/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.presenter.listusers.command.AutorizarUsuarioCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.EnviarNotificacaoCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.RemoverCommand;

/**
 *
 * @author heflainrmendes
 */
public class TabelaCarregadaState extends ListUserPresenterState {

    public TabelaCarregadaState(ListUserPresenter presenter) {
        super(presenter);

        view.getBtnAutorizar().setEnabled(true);
        view.getBtnEnviarNotificacao().setEnabled(true);
        view.getBtnExcluir().setEnabled(true);
    }

    @Override
    public void autorizar() {
        new AutorizarUsuarioCommand(presenter, view).executar();
    }

    @Override
    public void remover() {
        new RemoverCommand(presenter, view).executar();
    }

    @Override
    public void enviarNotificacao() {
        new EnviarNotificacaoCommand(presenter, view).executar();
    }
}
