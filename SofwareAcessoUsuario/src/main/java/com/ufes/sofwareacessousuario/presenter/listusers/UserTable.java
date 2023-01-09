/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.dao.service.UserRetorno;
import com.ufes.sofwareacessousuario.model.UserRegistro;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heflain
 */
public class UserTable extends AbstractTableModel {

    private List<UserRetorno> users;
    private String[] colunas = {"id", "nome", "autorizado"};

    public void setUsers(List<UserRetorno> users) {
        this.users = users;
        fireTableDataChanged();
    }
    
    public void atualizarTabela(){
        fireTableDataChanged();
    }

    public UserRetorno getUser(int rowIndex) {
        return users.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int column) {
            return colunas[column];
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserRetorno user = users.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getName();
            case 2:
                return user.getState();
            default:
                throw new AssertionError();
        }
    }
}
