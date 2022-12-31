/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heflain
 */
public class NotificationTable extends AbstractTableModel  {
    private List<notificationRetorno> list = new ArrayList();
    private String[] colunas = {"Remetente", "Assunto", "Lido"};

    public void setList(List<notificationRetorno> list) {
        this.list.clear();
        this.list.addAll(list);
        fireTableDataChanged();
        
    }

    @Override
    public String getColumnName(int column) {
            return colunas[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.list.get(rowIndex).setEstado("SIM");
         fireTableDataChanged();
    }
    
    

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var notificacao = list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return notificacao.getRemetente();
            case 1:
                return notificacao.getAssunto();
            case 2:
                return notificacao.getEstado();
            default:
                throw new AssertionError();
        }
    }
    
    public long getId(int rowIndex){
        return list.get(rowIndex).getId();
    }
    
    public String getMensagem(int rowIndex){
        return list.get(rowIndex).getMensagem();
    }
}
