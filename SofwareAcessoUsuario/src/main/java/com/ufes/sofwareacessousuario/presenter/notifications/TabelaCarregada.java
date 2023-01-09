/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.dao.service.UsuarioLogadoService;
import com.ufes.sofwareacessousuario.observable.EventListerners;
import com.ufes.sofwareacessousuario.presenter.notifications.command.LerCommand;

/**
 *
 * @author Heflains
 */
public class TabelaCarregada extends NotificationsPresenterState implements EventListerners{

    public TabelaCarregada(NotificationsPresenter presenter) {
        super(presenter);
        UsuarioLogadoService.getInstance().subcribe(this);
        presenter.view.getBtnLer().setEnabled(true);
    }

    @Override
    public void ler() {
        new LerCommand().executar(presenter.view, presenter.table);
    }

    @Override
    public void update(String mensagem) {
        if(mensagem.equals(UsuarioLogadoService.MARCADO_LIDO)){
            new CarregandoTabela(presenter);
            UsuarioLogadoService.getInstance().unsubcribe(this);
        }
    }
}
