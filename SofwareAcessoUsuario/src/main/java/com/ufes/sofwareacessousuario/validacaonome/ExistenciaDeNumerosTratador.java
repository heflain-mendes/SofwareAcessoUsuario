/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.validacaonome;

import java.util.regex.Pattern;

/**
 *
 * @author Heflain
 */
public class ExistenciaDeNumerosTratador implements ITratadorNome{

    @Override
    public String verificar(String nome) {
        Pattern p = Pattern.compile("[0-9]");
        if(p.matcher(nome).find()){
            return "não possuir digitos";
        }
        
        return null;
    }
}
