/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.validacaosenha;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class ValidadorSenha {

    private List<TratadorSenha> tratadores;

    public ValidadorSenha() {
        tratadores = new ArrayList();
        tratadores.add(new ConcretoTratador());
    }

    public List<String> verificar(String senha) {
        List<String> recusas = new ArrayList();

        for (var t : tratadores) {
            for (var s : t.verificar(senha)) {
                recusas.add(s);
            }
        }
        
        return recusas;
    }
}
