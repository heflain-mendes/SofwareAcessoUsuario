/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.notifications.command.FecharCommand;
import com.ufes.sofwareacessousuario.presenter.notifications.command.LerCommand;
import com.ufes.sofwareacessousuario.presenter.principal.PrincipalPresenter;

/**
 *
 * @author Heflains
 */
public class TabelaCarregada extends NotificacaoPresenterState implements EventListerners{

    public TabelaCarregada(NotificacaoPresenter presenter, 
            PrincipalPresenter principalPresenter) {
        super(presenter, principalPresenter);
        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
        presenter.view.getBtnLer().setEnabled(true);
        presenter.view.getBtnFechar().setEnabled(true);
    }

    @Override
    public void ler() {
        new LerCommand(presenter.view, presenter.table).executar();
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoServiceProxy.MARCADO_LIDO)){
            new CarregandoTabela(presenter, principalPresenter);
            UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
        }
    }

    @Override
    public void fechar() {
        new FecharCommand(presenter.view, principalPresenter).executar();
    }
}
