/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import daos.DaoCategoria;
import entidades.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.fCadCategoria;
import view.fConsCategoria;
import view.modelCategoria;

/**
 *
 * @author aluno
 */
public class ControlCategoria {

    private fCadCategoria fCadCategoria;
    private daos.DaoCategoria daoCategoria;
    private Categoria categoriaSelecionada;
    private modelCategoria modelCategoria;
    private fConsCategoria consultacategoria;

    public ControlCategoria() {
        this.fCadCategoria = new fCadCategoria(null, true);
        this.modelCategoria = new modelCategoria();
        this.consultacategoria = new fConsCategoria(null, true);
        daoCategoria = new DaoCategoria();
        inicilizarComponentes();
    }

    public void cadastrarCategoria() {
        this.fCadCategoria.setVisible(true);
    }

    public void consultarCategoria() {
        carregarCategoria();
        this.consultacategoria.setVisible(true);
    }

    public void gravarCategoria() {
        if (categoriaSelecionada == null) {
            String nome = fCadCategoria.nomeCadCategoria.getText();
            Categoria c = new Categoria(nome);
            if (daoCategoria.inserir(c)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
                fCadCategoria.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
                limpar();
                fCadCategoria.setVisible(false);
            }
        } else {
            categoriaSelecionada.setNome(fCadCategoria.nomeCadCategoria.getText());
            if (daoCategoria.editar(categoriaSelecionada)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso");
                categoriaSelecionada = null;
                limpar();
                fCadCategoria.setVisible(false);
                consultacategoria.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar");
            }
        }
    }

    public void inicilizarComponentes() {
        consultacategoria.tblCategoria.setModel(modelCategoria);
        fCadCategoria.btnGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarCategoria();
            }
        });

        fCadCategoria.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        consultacategoria.btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        consultacategoria.btnConsCategoriaEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCategoria();
            }
        });
    }

    public void editarCategoria() {
        int linhaselecionada = consultacategoria.tblCategoria.getSelectedRow();
        if (linhaselecionada >= 0) {
            categoriaSelecionada = modelCategoria.getCategoria(linhaselecionada);
            fCadCategoria.nomeCadCategoria.setText(categoriaSelecionada.getNome());
            consultacategoria.setVisible(false);
            fCadCategoria.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos uma linha");
        }
    }

    public void carregarCategoria() {
        modelCategoria.limpar();
        for (Categoria c : daoCategoria.listar()) {
            modelCategoria.inserirCategoria(c);
        }
    }

    public void cancelar(){
        categoriaSelecionada = null;
        limpar();
        consultacategoria.setVisible(false);
    }
    
    public void limpar() {
        fCadCategoria.nomeCadCategoria.setText("");
    }

    public void excluir() {
        int linhaselecionada = consultacategoria.tblCategoria.getSelectedRow();
        if (linhaselecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Você deseja realmente excluir este registro?") == 0) {
                Categoria c = modelCategoria.getCategoria(linhaselecionada);
                if (daoCategoria.excluir(c)) {
                    JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
                    modelCategoria.excluirCategoria(linhaselecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro!");
        }
    }
}
