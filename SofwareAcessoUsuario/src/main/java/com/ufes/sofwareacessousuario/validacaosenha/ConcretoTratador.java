/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.sofwareacessousuario.validacaosenha;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;

/**
 *
 * @author heflainrmendes
 */
public class ConcretoTratador implements TratadorSenha{
    private ValidadorSenha validador;

    public ConcretoTratador() {
        this.validador = new ValidadorSenha();
    }
    
    @Override
    public List<String> verificar(String senha) {
        return validador.validar(senha);
    }
}
