/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.validacaonome;

/**
 *
 * @author Heflain
 */
public class TamanhoMinimoTratador implements ITratadorNome{
    private final int TAMANHO_MINIMO = 4;
    
    @Override
    public String verificar(String nome) {
        if(nome.length() < TAMANHO_MINIMO){
            return "ser maior que " + TAMANHO_MINIMO;
        }
        
        return null;
    }
}
