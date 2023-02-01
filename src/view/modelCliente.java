/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entidades.Categoria;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aluno
 */
public class modelCliente extends AbstractTableModel {

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Código";
            }
            case 1: {
                return "Cpf";
            }
            case 2: {
                return "Nome";
            }
            case 3: {
                return "E-mail";
            }
            case 4: {
                return "Endereço";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return c.getId();
            }
            case 1: {
                return c.getCpf();
            }
            case 2: {
                return c.getNome();
            }
            case 3: {
                return c.getEmail();
            }
            case 4: {
                return c.getEndereco();
            }
        }
        return null;
    }

    public void limpar() {
        clientes.clear();
    }

    public Cliente getCliente(int i){
        return clientes.get(i);
    }
    
    public void excluirCliente(int i){
        clientes.remove(i);
        fireTableRowsDeleted(i, i);
    }
    
    public void inserirCliente(Cliente c){
        clientes.add(c);
        fireTableRowsInserted(clientes.size()-1, clientes.size()-1);
    } 
}
