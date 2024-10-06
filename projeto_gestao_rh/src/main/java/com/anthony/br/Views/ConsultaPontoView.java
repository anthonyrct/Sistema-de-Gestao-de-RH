package com.anthony.br.Views;

import com.anthony.br.Controllers.PontoEletricoController;
import com.anthony.br.Models.PontoEletrico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaPontoView extends JFrame {
    private PontoEletricoController pontoEletricoController;
    private Long funcionarioId; 

    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultaPontoView(Long funcionarioId) {
        this.funcionarioId = funcionarioId; // Armazena o ID do funcionário
        this.pontoEletricoController = new PontoEletricoController();

        setTitle("Consulta de Ponto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Adicione os componentes necessários para exibir os pontos do funcionário
        JLabel lblFuncionario = new JLabel("Consultando pontos de: " + funcionario.getNome());
        lblFuncionario.setBounds(10, 10, 300, 25);
        add(lblFuncionario);


        tableModel = new DefaultTableModel(new String[]{"ID", "Funcionário", "Data Registro", "Hora Entrada", "Hora Saída"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 560, 300);
        add(scrollPane);

        JButton btnConsultar = new JButton("Consultar Pontos");
        btnConsultar.setBounds(10, 320, 200, 25);
        add(btnConsultar);

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarPontos();
            }
        });
    }

    private void consultarPontos() {
        // Adicione a lógica para filtrar por funcionário, se necessário
        List<PontoEletrico> pontos = pontoEletricoController.buscarPontosPorFuncionario(funcionarioId); // Alterar para pegar o ID do funcionário logado

        // Limpa a tabela antes de adicionar os novos dados
        tableModel.setRowCount(0);
        
        for (PontoEletrico ponto : pontos) {
            tableModel.addRow(new Object[]{ponto.getId(), ponto.getFuncionario().getNome(), ponto.getDataRegistro(), ponto.getHoraEntrada(), ponto.getHoraSaida()});
        }
    }
}
