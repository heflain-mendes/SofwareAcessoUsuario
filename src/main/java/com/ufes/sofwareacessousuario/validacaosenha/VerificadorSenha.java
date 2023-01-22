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
public class VerificadorSenha {

    private List<IValidadorSenhaAdapter> validador;

    public VerificadorSenha() {
        validador = new ArrayList();
        validador.add(new ConcretoValidadorAdapter());
    }

    public List<String> verificar(String senha) {
        List<String> recusas = new ArrayList();

        for (var t : validador) {
            recusas.addAll(t.verificar(senha));
        }
        
        return recusas;
    }
}
