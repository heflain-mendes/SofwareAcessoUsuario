/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.notifications;

import com.ufes.sofwareacessousuario.util.NotificacaoRetorno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heflain
 */
public class NotificacaoTable extends AbstractTableModel  {
    private List<NotificacaoRetorno> list = new ArrayList();
    private String[] colunas = {"Remetente", "Assunto", "Lido"};

    public void setList(List<NotificacaoRetorno> list) {
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
         fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
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
    
    public NotificacaoRetorno getNotification(int rowIndex){
        return list.get(rowIndex);
    }
    
    public String getMensagem(int rowIndex){
        return list.get(rowIndex).getMensagem();
    }
}
