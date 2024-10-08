package com.anthony.br.Views;

// Importando as classes necessárias
import com.anthony.br.Controllers.PontoEletricoController;
import com.anthony.br.Models.Funcionario;
import com.anthony.br.Models.PontoEletrico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Classe que representa a interface de consulta de pontos
public class ConsultaPontoView extends JPanel { // Mude de JFrame para JPanel
    private PontoEletricoController pontoEletricoController; // Controlador responsável por gerenciar pontos
    private Funcionario funcionario; // Funcionário cujos pontos estão sendo consultados
    private Long funcionarioId; // ID do funcionário

    private JTable table; // Tabela para exibir os registros de ponto
    private DefaultTableModel tableModel; // Modelo da tabela

    // Construtor da classe que recebe um objeto Funcionario
    public ConsultaPontoView(Funcionario funcionario) {
        this.funcionario = funcionario; // Atribui o funcionário passado
        this.funcionarioId = funcionario.getId(); // Pegando o ID do funcionário logado
        this.pontoEletricoController = new PontoEletricoController(); // Inicializando o controlador

        setLayout(null); // Continua usando layout absoluto, mas agora no JPanel

        // Adicionando componentes à interface
        JLabel lblFuncionario = new JLabel("Consultando pontos de: " + funcionario.getNome());
        lblFuncionario.setBounds(10, 10, 300, 25); // Define a posição e o tamanho do label
        add(lblFuncionario); // Adiciona o label ao painel

        // Criação do modelo de tabela com as colunas
        tableModel = new DefaultTableModel(new String[] { "ID", "Funcionário", "Data Registro", "Hora Entrada",
                "Hora Saída", "Horas Trabalhadas", "Horas Extras" }, 0);
        table = new JTable(tableModel); // Inicializa a tabela com o modelo
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona a tabela a um painel de rolagem
        scrollPane.setBounds(10, 50, 560, 250); // Ajuste do tamanho da tabela
        add(scrollPane); // Adiciona o painel de rolagem ao painel

        // Botão para consultar pontos
        JButton btnConsultar = new JButton("Consultar Pontos");
        btnConsultar.setBounds(10, 320, 200, 25); // Define a posição e o tamanho do botão
        add(btnConsultar); // Adiciona o botão ao painel

        // Ação do botão para consultar pontos
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarPontos(); // Chama o método para consultar pontos ao clicar
            }
        });
    }

    // Método que busca e exibe os pontos do funcionário
    void consultarPontos() {
        // Busca os pontos do funcionário usando o controlador
        List<PontoEletrico> pontos = pontoEletricoController.buscarPontosPorFuncionario(funcionarioId);

        // Limpa a tabela antes de adicionar novos dados
        tableModel.setRowCount(0); // Remove todas as linhas da tabela

        // Verifica se não foram encontrados pontos
        if (pontos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum ponto encontrado para o funcionário " + funcionario.getNome() + ".", "Consulta de Ponto",
                    JOptionPane.INFORMATION_MESSAGE); // Mensagem informando que não há pontos
        } else {
            // Adiciona cada ponto encontrado à tabela
            for (PontoEletrico ponto : pontos) {
                tableModel.addRow(new Object[] {
                        ponto.getId(), // ID do ponto
                        ponto.getFuncionario().getNome(), // Nome do funcionário
                        ponto.getDataRegistro(), // Data de registro
                        ponto.getHoraEntrada(), // Hora de entrada
                        ponto.getHoraSaida(), // Hora de saída
                        ponto.getHorasTrabalhadas() != null ? ponto.getHorasTrabalhadas().toHours() + " horas" : "N/A", // Horas trabalhadas
                        ponto.getHorasExtras() != null ? ponto.getHorasExtras().toHours() + " horas" : "N/A" // Horas extras
                });
            }
            // Mensagem informando a quantidade de pontos encontrados
            JOptionPane.showMessageDialog(this,
                    "Consulta realizada com sucesso! " + pontos.size() + " ponto(s) encontrado(s).",
                    "Consulta de Ponto", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
