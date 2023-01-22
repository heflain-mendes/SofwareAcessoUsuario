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
class ConcretoValidadorAdapter implements IValidadorSenhaAdapter{
    private ValidadorSenha validador;

    public ConcretoValidadorAdapter() {
        this.validador = new ValidadorSenha();
    }
    
    @Override
    public List<String> verificar(String senha) {
        return validador.validar(senha);
    }
}
