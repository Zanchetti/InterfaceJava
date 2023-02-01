/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aluno
 */
public class modelCategoria extends AbstractTableModel {

    private List<Categoria> categorias = new ArrayList<>();

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Código";
            }
            case 1: {
                return "Nome";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria c = categorias.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return c.getId();
            }
            case 1: {
                return c.getNome();
            }
        }
        return null;
    }

    public void limpar() {
        categorias.clear();
    }

    public Categoria getCategoria(int i){
        return categorias.get(i);
    }
    
    public void excluirCategoria(int i){
        categorias.remove(i);
        fireTableRowsDeleted(i, i);
    }
    
    public void inserirCategoria(Categoria c){
        categorias.add(c);
        fireTableRowsInserted(categorias.size()-1, categorias.size()-1);
    } 
}
