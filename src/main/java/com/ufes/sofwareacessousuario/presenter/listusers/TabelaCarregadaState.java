/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.util.UsuariosDAOServiceProxy;
import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.listusers.command.AutorizarUsuarioCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.EnviarNotificacaoCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.FecharCommand;
import com.ufes.sofwareacessousuario.presenter.listusers.command.RemoverCommand;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author heflainrmendes
 */
public class TabelaCarregadaState extends ListUserPresenterState implements EventListerners {
    private PrincipalPresenter principalPresenter;
    
    public TabelaCarregadaState(ListaUsuarioPresenter presenter, PrincipalPresenter principalPresenter) {
        super(presenter);
        this.principalPresenter = principalPresenter;
        
        view.getBtnAutorizar().setEnabled(true);
        view.getBtnEnviarNotificacao().setEnabled(true);
        view.getBtnExcluir().setEnabled(true);

        UsuariosDAOServiceProxy.getInstance().subcribe(this);
        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
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
        new EnviarNotificacaoCommand(presenter, view, model, principalPresenter).executar();
    }

    @Override
    public void update(String mensagem) {
        if (
                mensagem.equals(UsuariosDAOServiceProxy.USUARIO_ADICIONADO) ||
                mensagem.equals(UsuariosDAOServiceProxy.USUARIO_AUTORIZADO) ||
                mensagem.equals(UsuariosDAOServiceProxy.USUARIO_REMOVIDO)) {
            new CarregandoTabelaState(presenter, principalPresenter);
            unsubcribe();
        } else if (mensagem.equals(UsuarioLogadoServiceProxy.USUARIO_DESLOGADO)) {
            unsubcribe();
        }
    }

    private void unsubcribe() {
        UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
        UsuariosDAOServiceProxy.getInstance().unsubcribe(this);
    }

    @Override
    public void fechar() {
        new FecharCommand(presenter, view, model, principalPresenter).executar();
        unsubcribe();
    }
}
