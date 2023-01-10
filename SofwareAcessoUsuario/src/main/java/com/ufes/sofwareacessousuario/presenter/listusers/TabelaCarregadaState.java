/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.dao.service.UsuariosDAOService;
import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.listusers.command.AutorizarUsuarioCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.EnviarNotificacaoCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.FecharCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.RemoverCommand;

/**
 *
 * @author heflainrmendes
 */
public class TabelaCarregadaState extends ListUserPresenterState implements EventListerners {

    public TabelaCarregadaState(ListaUsuarioPresenter presenter) {
        super(presenter);

        view.getBtnAutorizar().setEnabled(true);
        view.getBtnEnviarNotificacao().setEnabled(true);
        view.getBtnExcluir().setEnabled(true);

        UsuariosDAOService.getInstance().subcribe(this);
        UsuarioLogadoService.getInstance().subcribe(this);
    }

    @Override
    public void autorizar() {
        new AutorizarUsuarioCommand(presenter, view, model).executar();
    }

    @Override
    public void remover() {
        new RemoverCommand(presenter, view, model).executar();
    }

    @Override
    public void enviarNotificacao() {
        new EnviarNotificacaoCommand(presenter, view, model).executar();
    }

    @Override
    public void update(String mensagem) {
        if (
                mensagem.equals(UsuariosDAOService.USUARIO_ADICIONADO) ||
                mensagem.equals(UsuariosDAOService.USUARIO_AUTORIZADO) ||
                mensagem.equals(UsuariosDAOService.USUARIO_REMOVIDO)) {
            new CarregandoTabelaState(presenter);
            unsubcribe();
        } else if (mensagem.equals(UsuarioLogadoService.USUARIO_DESLOGADO)) {
            unsubcribe();
        }
    }

    private void unsubcribe() {
        UsuarioLogadoService.getInstance().unsubcribe(this);
        UsuariosDAOService.getInstance().unsubcribe(this);
    }

    @Override
    public void fechar() {
        new FecharCommand(presenter, view, model).executar();
        unsubcribe();
    }
}
