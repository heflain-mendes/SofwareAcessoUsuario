/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.util.UsuarioLogadoServiceProxy;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.notifications.command.LerCommand;

/**
 *
 * @author Heflains
 */
public class TabelaCarregada extends NotificacaoPresenterState implements EventListerners{

    public TabelaCarregada(NotificacaoPresenter presenter) {
        super(presenter);
        UsuarioLogadoServiceProxy.getInstance().subcribe(this);
        presenter.view.getBtnLer().setEnabled(true);
    }

    @Override
    public void ler() {
        new LerCommand().executar(presenter.view, presenter.table);
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoServiceProxy.MARCADO_LIDO)){
            new CarregandoTabela(presenter);
            UsuarioLogadoServiceProxy.getInstance().unsubcribe(this);
        }
    }
}
