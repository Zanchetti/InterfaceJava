/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.fConsCategoria;
import view.fPrincipal;

/**
 *
 * @author aluno
 */
public class ControlPrincipal {

    private fPrincipal fPrincipal;
    private ControlCategoria controlCategoria;
    private ControlCliente controlCliente;
    private ControlProduto controlProduto;

    public ControlPrincipal() {
        fPrincipal = new fPrincipal();
        controlCategoria = new ControlCategoria();
        controlCliente = new ControlCliente();
        controlProduto = new ControlProduto();
        inicializarComponentes();
    }

    public void cadastrarCategoria() {
        controlCategoria.cadastrarCategoria();
    }

    public void cadastrarCliente() {
        controlCliente.cadastrarCliente();
    }

    public void consultarCategoria() {
        controlCategoria.consultarCategoria();
    }
    
    public void cadastrarProduto(){
        controlProduto.CadastrarProduto();
    }

    public void consultarCliente() {
        controlCliente.consultarCliente();
    }
    
    public void consultarProduto(){
        controlProduto.consultarProduto();
    }

    public void executar() {
        fPrincipal.setVisible(true);
    }

    private void inicializarComponentes() {
        fPrincipal.miCadCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCategoria();
            }
        });

        fPrincipal.miCadCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        fPrincipal.miConsCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarCategoria();
            }
        });

        fPrincipal.miConsCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarCliente();
            }
        });
        
        fPrincipal.miCadProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });
        
        fPrincipal.miConsProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarProduto();
            }
        });
    }
}
