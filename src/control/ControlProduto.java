/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import daos.DaoCategoria;
import daos.DaoProduto;
import entidades.Categoria;
import entidades.Cliente;
import entidades.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import view.fCadProduto;
import view.fConsProduto;
import view.modelCategoria;
import view.modelProduto;

/**
 *
 * @author aluno
 */
public class ControlProduto {

    private fCadProduto fCadProduto;
    private DaoProduto daoProduto;
    private Produto produtoSelecionado;
    private modelCategoria modelCategoria;
    private DaoCategoria daoCategoria;
    private fConsProduto fConsProduto;
    private modelProduto modelProduto;

    public ControlProduto() {
        this.fCadProduto = new fCadProduto(null, true);
        this.produtoSelecionado = null;
        this.fConsProduto = new fConsProduto(null, true);
        this.modelCategoria = new modelCategoria();
        this.modelProduto = new modelProduto();
        this.daoCategoria = new DaoCategoria();
        daoProduto = new DaoProduto();
        inicializarComponente();
    }
    
    public void consultarProduto() {
        carregarProduto();
        this.fConsProduto.setVisible(true);
    }

    public void CadastrarProduto() {
        fCadProduto.cbCategoria.removeAllItems();
        carregarCategorias();
        this.fCadProduto.setVisible(true);
    }

    public void gravarProduto() {
        if (produtoSelecionado == null) {
            int codigo = Integer.parseInt(fCadProduto.codigoField.getText());
            String descricao = fCadProduto.descricaoField.getText();
            double valor = Double.parseDouble(fCadProduto.valorField.getText());
            Categoria c = daoCategoria.selecionar((String) fCadProduto.cbCategoria.getSelectedItem());
            Produto p = new Produto(codigo, descricao, valor, c);
            if (daoProduto.inserir(p)) {
                JOptionPane.showMessageDialog(null, "Sucesso ao inserir um produto!");
                limpar();
                fCadProduto.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir produto;");
                limpar();
                fCadProduto.setVisible(false);
            }
        } else {
            produtoSelecionado.setCodigo(Integer.parseInt(fCadProduto.codigoField.getText()));
            produtoSelecionado.setDescricao(fCadProduto.descricaoField.getText());
            produtoSelecionado.setValor(Double.parseDouble(fCadProduto.valorField.getText()));
            produtoSelecionado.setCategoria(daoCategoria.selecionar((String) fCadProduto.cbCategoria.getSelectedItem()));
            if (daoProduto.editar(produtoSelecionado)) {
                JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
                limpar();
                fCadProduto.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao editar o produto");
                limpar();
                fCadProduto.setVisible(false);
            }
        }
    }

    public void inicializarComponente() {
        fConsProduto.tblProduto.setModel(modelProduto);
        fCadProduto.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        fCadProduto.btnGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarProduto();
            }
        });
        
        fConsProduto.btnEditarCons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        
        fConsProduto.btnExcluirCons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
    }

    public void cancelar() {
        produtoSelecionado = null;
        limpar();
    }
    
    public void editar(){
       int linhaselecionada = fConsProduto.tblProduto.getSelectedRow();
        if (linhaselecionada >= 0) {
            produtoSelecionado=modelProduto.getProduto(linhaselecionada);
            fCadProduto.codigoField.setText(Integer.toString(produtoSelecionado.getCodigo()));
            //só replica a linha de cima
            //---------------------------------
            fConsProduto.setVisible(false);
            fCadProduto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos uma linha");
        } 
    }
    
        public void excluir() {
        int linhaselecionada = fConsProduto.tblProduto.getSelectedRow();
        if (linhaselecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Você deseja realmente excluir este registro?") == 0) {
                Produto p = modelProduto.getProduto(linhaselecionada);
                if (daoProduto.excluir(p)) {
                    JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
                    modelProduto.excluirProduto(linhaselecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro!");
        }
    }

    public void limpar() {
        fCadProduto.codigoField.setText("");
        fCadProduto.descricaoField.setText("");
        fCadProduto.valorField.setText("");
    }

    public void carregarCategorias() {
        for (Categoria c : daoCategoria.listar()) {
            fCadProduto.cbCategoria.addItem(c.getNome());
        }
    }
    
    public void carregarProduto() {
        modelProduto.limpar();
        for (Produto p : daoProduto.listar()) {
            modelProduto.inserirCliente(p);
        }
    }
}
