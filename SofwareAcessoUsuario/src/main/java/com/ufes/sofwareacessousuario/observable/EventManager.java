/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.observable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class EventManager {
    private List<EventListerners> listerners = new ArrayList();

    public void subscribe(EventListerners el) {
        if (el != null) {
            listerners.add(el);
        }
    }

    public void notify(String mensagem) {
        for (var l : listerners) {
            l.update(mensagem);
        }
    }
}
