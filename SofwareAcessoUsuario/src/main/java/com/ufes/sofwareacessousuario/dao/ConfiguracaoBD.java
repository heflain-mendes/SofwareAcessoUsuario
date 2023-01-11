/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao;

import com.ufes.sofwareacessousuario.dao.IAbstractFactoryDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heflain
 */
public class ConfiguracaoBD {
    private List<IAbstractFactoryDAO> fabricas;

    public ConfiguracaoBD() {
        fabricas = new ArrayList();
        
        fabricas.add(new SqliteFactoryDAO());
    }
    
    public IAbstractFactoryDAO getFabrica(String SGBD){
        IAbstractFactoryDAO fabrica;
        
        for(var f : fabricas){
            if(f.getTipoPersistencia().equals(SGBD)){
                return f;
            }
        }
        
        return null;
    }
    
    public List<String> getSBDBs(){
        List<String> SGDBs = new ArrayList();
        
        for(var x : fabricas){
            SGDBs.add(x.getTipoPersistencia());
        }
        
        return SGDBs;
    }
}
