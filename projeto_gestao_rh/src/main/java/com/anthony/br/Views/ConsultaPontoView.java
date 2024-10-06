package com.anthony.br.Views;

import com.anthony.br.Controllers.PontoEletricoController;
import com.anthony.br.Models.Funcionario;
import com.anthony.br.Models.PontoEletrico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaPontoView extends JFrame {
    private PontoEletricoController pontoEletricoController;
    private Funcionario funcionario;
    private Long funcionarioId;

    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultaPontoView(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.funcionarioId = funcionario.getId(); // Pegando o ID do funcionário logado
        this.pontoEletricoController = new PontoEletricoController();

        setTitle("Consulta de Ponto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Adicionando componentes
        JLabel lblFuncionario = new JLabel("Consultando pontos de: " + funcionario.getNome());
        lblFuncionario.setBounds(10, 10, 300, 25);
        add(lblFuncionario);

        tableModel = new DefaultTableModel(new String[] { "ID", "Funcionário", "Data Registro", "Hora Entrada",
                "Hora Saída", "Horas Trabalhadas", "Horas Extras" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 560, 250); // Ajuste do tamanho da tabela
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
        List<PontoEletrico> pontos = pontoEletricoController.buscarPontosPorFuncionario(funcionarioId);

        // Limpa a tabela antes de adicionar novos dados
        tableModel.setRowCount(0);

        if (pontos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum ponto encontrado para o funcionário " + funcionario.getNome() + ".", "Consulta de Ponto",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (PontoEletrico ponto : pontos) {
                tableModel.addRow(new Object[] {
                        ponto.getId(),
                        ponto.getFuncionario().getNome(),
                        ponto.getDataRegistro(),
                        ponto.getHoraEntrada(),
                        ponto.getHoraSaida(),
                        ponto.getHorasTrabalhadas() != null ? ponto.getHorasTrabalhadas().toHours() + " horas" : "N/A",
                        ponto.getHorasExtras() != null ? ponto.getHorasExtras().toHours() + " horas" : "N/A"
                });
            }
            JOptionPane.showMessageDialog(this,
                    "Consulta realizada com sucesso! " + pontos.size() + " ponto(s) encontrado(s).",
                    "Consulta de Ponto", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
