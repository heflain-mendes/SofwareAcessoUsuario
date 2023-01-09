/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario;

import com.ufes.sofwareacessousuario.validacaonome.ValidadorNome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
public class Teste {
    public static void main(String[] args) {
        List<String> recusasNome = new ArrayList();
        recusasNome.add("um");
        recusasNome.add("dois");
        recusasNome.add("tres");
        
        String retorno = String.join(
                ", ",
                recusasNome.subList(0, recusasNome.size() - 1));
        retorno += " e " + recusasNome.get(recusasNome.size() - 1);
    }
}
