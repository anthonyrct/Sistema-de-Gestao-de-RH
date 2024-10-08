package com.anthony.br.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.anthony.br.Controllers.FuncionarioController;
import com.anthony.br.Models.Funcionario;

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

    // Construtor da classe FuncionarioTable
    public FuncionarioTable() {
        setLayout(new BorderLayout());  // Define o layout do painel como BorderLayout

        // Definição das colunas da tabela
        String[] columnNames = { "Nome", "CPF", "Cargo", "Salário", "Departamento" }; 
        tableModel = new DefaultTableModel(columnNames, 0); // Modelo da tabela com as colunas definidas
        table = new JTable(tableModel);  // Cria a tabela com o modelo

        // Adiciona a tabela em um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Adiciona o JScrollPane ao centro do painel

        // Cria o botão de atualizar
        atualizarButton = new JButton("Atualizar");
        // Adiciona um ActionListener para o botão de atualizar
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();  // Chama o método que atualiza os dados da tabela quando o botão é clicado
            }
        });
        add(atualizarButton, BorderLayout.SOUTH);  // Adiciona o botão na parte inferior do painel

        // Carrega os dados iniciais
        atualizarTabela();  // Atualiza a tabela quando o painel é inicializado
    }

    // Método que atualiza os dados da tabela
    public void atualizarTabela() {
        // Limpa os dados existentes na tabela antes de adicionar novos
        tableModel.setRowCount(0);

        // Cria uma instância do controlador de funcionários para obter os dados
        FuncionarioController funcionarioController = new FuncionarioController();
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();  // Método para listar funcionários

        // Adiciona os dados dos funcionários na tabela
        for (Funcionario funcionario : funcionarios) {
            Object[] row = {
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getCargo(),
                    funcionario.getSalario(),
                    funcionario.getDepartamento()
            };
            tableModel.addRow(row);  // Adiciona uma linha na tabela para cada funcionário
        }
    }
}
