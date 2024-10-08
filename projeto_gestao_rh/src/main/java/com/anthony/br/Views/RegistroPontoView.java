package com.anthony.br.Views;

import com.anthony.br.Controllers.PontoEletricoController;
import com.anthony.br.Models.Funcionario;
import com.anthony.br.Models.PontoEletrico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistroPontoView extends JPanel {
    private PontoEletricoController pontoEletricoController;
    private Funcionario funcionario;
    private JanelaPrincipal janelaPrincipal; // Armazene a instância de JanelaPrincipal

    private JTextField txtHoraEntrada;
    private JTextField txtHoraSaida;
    private JButton btnRegistrar;
    private JButton btnConsultar;

    // Modifique o construtor para receber a instância de JanelaPrincipal
    public RegistroPontoView(Funcionario funcionario, JanelaPrincipal janelaPrincipal) {
        this.funcionario = funcionario;
        this.janelaPrincipal = janelaPrincipal; // Armazene a referência à instância de JanelaPrincipal
        this.pontoEletricoController = new PontoEletricoController();

        setLayout(null); // Continua usando layout absoluto, mas agora no JPanel

        JLabel lblHoraEntrada = new JLabel("Hora de Entrada (yyyy-MM-ddTHH:mm):");
        lblHoraEntrada.setBounds(10, 10, 200, 25);
        add(lblHoraEntrada);

        txtHoraEntrada = new JTextField();
        txtHoraEntrada.setBounds(220, 10, 150, 25);
        add(txtHoraEntrada);

        JLabel lblHoraSaida = new JLabel("Hora de Saída (yyyy-MM-ddTHH:mm):");
        lblHoraSaida.setBounds(10, 40, 200, 25);
        add(lblHoraSaida);

        txtHoraSaida = new JTextField();
        txtHoraSaida.setBounds(220, 40, 150, 25);
        add(txtHoraSaida);

        btnRegistrar = new JButton("Registrar Ponto");
        btnRegistrar.setBounds(10, 70, 360, 25);
        add(btnRegistrar);

        btnConsultar = new JButton("Consultar Pontos");
        btnConsultar.setBounds(10, 110, 360, 25);
        add(btnConsultar);

        // Ação do botão Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPonto();
            }
        });

        // Ação do botão Consultar
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConsultaPonto();
            }
        });
    }

    private void registrarPonto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            LocalDateTime horaEntrada = LocalDateTime.parse(txtHoraEntrada.getText(), formatter);
            LocalDateTime horaSaida = LocalDateTime.parse(txtHoraSaida.getText(), formatter);

            // Validação da Hora
            if (horaSaida.isBefore(horaEntrada)) {
                JOptionPane.showMessageDialog(this, "Hora de saída não pode ser anterior à hora de entrada.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            PontoEletrico ponto = new PontoEletrico();
            ponto.setFuncionario(funcionario);
            ponto.setHoraEntrada(horaEntrada);
            ponto.setHoraSaida(horaSaida);
            ponto.setDataRegistro(LocalDateTime.now());

            // Tente registrar o ponto no banco de dados
            pontoEletricoController.registrarPonto(ponto);
            JOptionPane.showMessageDialog(this, "Ponto registrado com sucesso! ID do ponto: " + ponto.getId(),
                    "Registro de Ponto", JOptionPane.INFORMATION_MESSAGE);
            txtHoraEntrada.setText("");
            txtHoraSaida.setText("");

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data/hora inválido. Use o formato: yyyy-MM-ddTHH:mm",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (javax.persistence.PersistenceException e) {
            // Tratamento específico para exceções de persistência
            JOptionPane.showMessageDialog(this,
                    "Erro ao registrar ponto: Problema de conexão com o banco de dados. Por favor, tente novamente mais tarde.",
                    "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Tratamento genérico para outras exceções
            JOptionPane.showMessageDialog(this, "Erro ao registrar ponto: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirConsultaPonto() {
        janelaPrincipal.trocarPainel("consultaPonto"); // Use a instância para chamar trocarPainel
    }

}
