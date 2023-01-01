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
    private List<EventListerners> listernersRemove = new ArrayList();

    public void subscribe(EventListerners el) {
        if (el != null) {
            listerners.add(el);
        }
    }
    
    public void unsubcribe(EventListerners el){
        if(el != null){
            listernersRemove.add(el);
        }
    }
    
    private void remove(){
        if(listernersRemove.size() > 0){
            for(var nr : listernersRemove){
                listerners.remove(nr);
            }
            
            listernersRemove.clear();
        }
    }

    public void notify(String mensagem) {
        remove();
        for (var l : listerners) {
            l.update(mensagem);
        }
    }
}
