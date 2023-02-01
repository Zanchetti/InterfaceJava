/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entidades.Categoria;
import entidades.Cliente;
import entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aluno
 */
public class modelProduto extends AbstractTableModel {

    private List<Produto> produtos = new ArrayList<>();

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

@Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Id";
            }
            case 1: {
                return "Código";
            }
            case 2: {
                return "Descrição";
            }
            case 3: {
                return "Valor";
            }
            case 4: {
                return "Categorias";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto p = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return p.getId();
            }
            case 1: {
                return p.getCodigo();
            }
            case 2: {
                return p.getDescricao();
            }
            case 3: {
                return p.getValor();
            }
            case 4: {
                return p.getCategoria().getNome();
            }
        }
        return null;
    }

    public void limpar() {
        produtos.clear();
    }

    public Produto getProduto(int i) {
        return produtos.get(i);
    }

    public void excluirProduto(int i) {
        produtos.remove(i);
        fireTableRowsDeleted(i, i);
    }

    public void inserirCliente(Produto p) {
        produtos.add(p);
        fireTableRowsInserted(produtos.size() - 1, produtos.size() - 1);
    }
}
