/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import daos.DaoCategoria;
import daos.DaoCliente;
import entidades.Categoria;
import entidades.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.FConsCliente;
import view.fCadCategoria;
import view.fCadCliente;
import view.fConsCategoria;
import view.modelCategoria;
import view.modelCliente;

/**
 *
 * @author aluno
 */
public class ControlCliente {

    private fCadCliente fcadcliente;
    private daos.DaoCliente daoCliente;
    private Cliente clienteSelecionada;
    private modelCliente modelCliente;
    private FConsCliente consultacliente;

    public ControlCliente() {
        this.fcadcliente = new fCadCliente(null, true);
        this.modelCliente = new modelCliente();
        this.consultacliente = new FConsCliente(null, true);
        daoCliente = new DaoCliente();
        inicilizarComponentes();
    }

    public void cadastrarCliente() {
        this.fcadcliente.setVisible(true);
    }

    public void consultarCliente() {
        carregarCliente();
        this.consultacliente.setVisible(true);
    }

    public void gravarCliente() {
        if (clienteSelecionada == null) {
            String cpf = fcadcliente.cpfField.getText();
            String nome = fcadcliente.nomeField.getText();
            String email = fcadcliente.emailField.getText();
            String endereco = fcadcliente.enderecoField.getText();
            Cliente c = new Cliente(cpf, nome, email, endereco);
            if (daoCliente.inserir(c)) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                limpar();
                fcadcliente.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir!");
                limpar();
                fcadcliente.setVisible(false);
            }
        } else {
            clienteSelecionada.setCpf(fcadcliente.cpfField.getText());
            clienteSelecionada.setNome(fcadcliente.nomeField.getText());
            clienteSelecionada.setEmail(fcadcliente.emailField.getText());
            clienteSelecionada.setEndereco(fcadcliente.enderecoField.getText());
            if (daoCliente.editar(clienteSelecionada)) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso");
                clienteSelecionada = null;
                limpar();
                fcadcliente.setVisible(false);
                consultacliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar");
            }
        }
    }

    public void inicilizarComponentes() {
        consultacliente.tblConsCliente.setModel(modelCliente);
        fcadcliente.btnGravarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravarCliente();
            }
        });

        fcadcliente.btnCancelarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        consultacliente.btnConsClienteExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        consultacliente.btnConsClienteEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
    }

    public void editarCliente() {
        int linhaselecionada = consultacliente.tblConsCliente.getSelectedRow();
        if (linhaselecionada >= 0) {
            clienteSelecionada = modelCliente.getCliente(linhaselecionada);
            fcadcliente.cpfField.setText(clienteSelecionada.getCpf());
            fcadcliente.nomeField.setText(clienteSelecionada.getNome());
            fcadcliente.emailField.setText(clienteSelecionada.getEmail());
            fcadcliente.enderecoField.setText(clienteSelecionada.getEndereco());
            consultacliente.setVisible(false);
            fcadcliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos uma linha");
        }
    }

    public void carregarCliente() {
        modelCliente.limpar();
        for (Cliente c : daoCliente.listar()) {
            modelCliente.inserirCliente(c);
        }
    }

    public void cancelar() {
        clienteSelecionada = null;
        limpar();
        fcadcliente.setVisible(false);
        consultacliente.setVisible(true);
    }

    public void limpar() {
        fcadcliente.cpfField.setText("");
        fcadcliente.nomeField.setText("");
        fcadcliente.emailField.setText("");
        fcadcliente.enderecoField.setText("");
    }

    public void excluir() {
        int linhaselecionada = consultacliente.tblConsCliente.getSelectedRow();
        if (linhaselecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Você deseja realmente excluir este registro?") == 0) {
                Cliente c = modelCliente.getCliente(linhaselecionada);
                if (daoCliente.excluir(c)) {
                    JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
                    modelCliente.excluirCliente(linhaselecionada);
                    consultacliente.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro!");
        }
    }
}
