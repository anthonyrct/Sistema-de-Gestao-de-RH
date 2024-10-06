package com.anthony.br.Views;

import com.anthony.br.Controllers.PontoEletricoController;
import com.anthony.br.Models.Funcionario;
import com.anthony.br.Models.PontoEletrico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class RegistroPontoView extends JFrame {
    private PontoEletricoController pontoEletricoController;
    private Funcionario funcionario; // Supondo que você tenha o funcionário logado

    private JTextField txtHoraEntrada;
    private JTextField txtHoraSaida;
    private JButton btnConsultar;

    public RegistroPontoView(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.pontoEletricoController = new PontoEletricoController();

        setTitle("Registro de Ponto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblHoraEntrada = new JLabel("Hora de Entrada:");
        lblHoraEntrada.setBounds(10, 10, 150, 25);
        add(lblHoraEntrada);

        txtHoraEntrada = new JTextField();
        txtHoraEntrada.setBounds(150, 10, 120, 25);
        add(txtHoraEntrada);

        JLabel lblHoraSaida = new JLabel("Hora de Saída:");
        lblHoraSaida.setBounds(10, 40, 150, 25);
        add(lblHoraSaida);

        txtHoraSaida = new JTextField();
        txtHoraSaida.setBounds(150, 40, 120, 25);
        add(txtHoraSaida);

        JButton btnRegistrar = new JButton("Registrar Ponto");
        btnRegistrar.setBounds(10, 70, 260, 25);
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPonto();
            }
        });

            // Adicione a ação do botão de consulta
            btnConsultar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirConsultaPonto(); // Chama a tela de consulta
                }
            });
    }

    private void registrarPonto() {
        LocalDateTime horaEntrada = LocalDateTime.parse(txtHoraEntrada.getText());
        LocalDateTime horaSaida = LocalDateTime.parse(txtHoraSaida.getText());

        PontoEletrico ponto = new PontoEletrico();
        ponto.setFuncionario(funcionario);
        ponto.setHoraEntrada(horaEntrada);
        ponto.setHoraSaida(horaSaida);
        ponto.setDataRegistro(LocalDateTime.now());

        pontoEletricoController.registrarPonto(ponto);
        JOptionPane.showMessageDialog(this, "Ponto registrado com sucesso!");
        txtHoraEntrada.setText("");
        txtHoraSaida.setText("");
    }

    private void abrirConsultaPonto() {
        ConsultaPontoView consultaPontoView = new ConsultaPontoView(funcionario); // Passa o objeto Funcionario
    consultaPontoView.setVisible(true); // Mostra a tela de consulta
    this.dispose(); // Fecha a tela de registro de ponto, se necessário
    }
}
