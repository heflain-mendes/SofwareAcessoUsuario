/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.validacaonome;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
public class ValidadorNome {
    private final List<ITratadorNome> tratadores;

    public ValidadorNome() {
        this.tratadores = new ArrayList();
        
        tratadores.add(new TamanhoMinimoTratador());
        tratadores.add(new ExistenciaDeNumerosTratador());
    }
    
    public List<String> validar(String nome){
        List<String> recusas = new ArrayList();
        String retorno;
        for(var t : tratadores){
            retorno = t.verificar(nome);
            if(retorno != null){
                recusas.add(retorno);
            }
        }
        return recusas;
    }
}
