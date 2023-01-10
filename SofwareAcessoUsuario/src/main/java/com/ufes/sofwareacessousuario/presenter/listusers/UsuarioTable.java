/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.presenter.listusers;

import com.ufes.sofwareacessousuario.dao.service.UsuarioRetorno;
import com.ufes.sofwareacessousuario.model.UsuarioRegistro;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heflain
 */
public class UsuarioTable extends AbstractTableModel {

    private List<UsuarioRetorno> users;
    private String[] colunas = {"id", "nome", "autorizado"};

    public void setUsers(List<UsuarioRetorno> users) {
        this.users = users;
        fireTableDataChanged();
    }
    
    public void atualizarTabela(){
        fireTableDataChanged();
    }

    public UsuarioRetorno getUser(int rowIndex) {
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
        UsuarioRetorno user = users.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getNome();
            case 2:
                return user.getEstado();
            default:
                throw new AssertionError();
        }
    }
}
