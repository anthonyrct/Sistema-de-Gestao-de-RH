package com.anthony.br.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.anthony.br.Controllers.FuncionarioController;
import com.anthony.br.Models.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FuncionarioTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton atualizarButton;

    public FuncionarioTable() {
        setLayout(new BorderLayout());

        // Definição do modelo da tabela
        String[] columnNames = { "Nome", "CPF", "Cargo", "Salário", "Departamento" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Adicionando a tabela em um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista de funcionários
        atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela(); // Chama o método de atualização da tabela
            }
        });
        add(atualizarButton, BorderLayout.SOUTH);

        // Carregar dados iniciais
        atualizarTabela(); // Atualiza a tabela ao inicializar
    }

    // Método para atualizar a tabela
    public void atualizarTabela() {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Recupera a lista de funcionários do controlador
        FuncionarioController funcionarioController = new FuncionarioController();
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios(); // Método que você deve implementar
                                                                                     // no controlador

        // Adiciona os funcionários à tabela
        for (Funcionario funcionario : funcionarios) {
            Object[] row = {
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getCargo(),
                    funcionario.getSalario(),
                    funcionario.getDepartamento()
            };
            tableModel.addRow(row);
        }
    }
}
