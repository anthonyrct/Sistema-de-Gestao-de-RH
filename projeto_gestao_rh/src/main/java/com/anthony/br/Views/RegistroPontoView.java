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
    // Controller responsável pela lógica de negócios relacionada ao ponto eletrônico
    private PontoEletricoController pontoEletricoController;
    private Funcionario funcionario;  // Funcionário logado que está registrando o ponto
    private JanelaPrincipal janelaPrincipal;  // Referência à janela principal do sistema para troca de telas

    // Campos de entrada de texto e botões para registro de ponto
    private JTextField txtHoraEntrada;
    private JTextField txtHoraSaida;
    private JButton btnRegistrar;
    private JButton btnConsultar;

    // Construtor que inicializa a view de registro de ponto
    // Recebe o funcionário logado e a referência à janela principal para trocar de tela
    public RegistroPontoView(Funcionario funcionario, JanelaPrincipal janelaPrincipal) {
        this.funcionario = funcionario;
        this.janelaPrincipal = janelaPrincipal;  // Guarda a referência à janela principal
        this.pontoEletricoController = new PontoEletricoController();

        // Definindo o layout do painel como nulo para usar posicionamento absoluto
        setLayout(null);

        // Label e campo de texto para inserir a hora de entrada
        JLabel lblHoraEntrada = new JLabel("Hora de Entrada (yyyy-MM-ddTHH:mm):");
        lblHoraEntrada.setBounds(10, 10, 200, 25);
        add(lblHoraEntrada);

        txtHoraEntrada = new JTextField();
        txtHoraEntrada.setBounds(220, 10, 150, 25);
        add(txtHoraEntrada);

        // Label e campo de texto para inserir a hora de saída
        JLabel lblHoraSaida = new JLabel("Hora de Saída (yyyy-MM-ddTHH:mm):");
        lblHoraSaida.setBounds(10, 40, 200, 25);
        add(lblHoraSaida);

        txtHoraSaida = new JTextField();
        txtHoraSaida.setBounds(220, 40, 150, 25);
        add(txtHoraSaida);

        // Botão para registrar o ponto eletrônico
        btnRegistrar = new JButton("Registrar Ponto");
        btnRegistrar.setBounds(10, 70, 360, 25);
        add(btnRegistrar);

        // Botão para consultar os pontos já registrados
        btnConsultar = new JButton("Consultar Pontos");
        btnConsultar.setBounds(10, 110, 360, 25);
        add(btnConsultar);

        // Ação do botão "Registrar Ponto"
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPonto();  // Chama o método que registra o ponto no banco de dados
            }
        });

        // Ação do botão "Consultar Pontos"
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConsultaPonto();  // Abre a tela de consulta de pontos
            }
        });
    }

    // Método responsável por registrar o ponto eletrônico no sistema
    private void registrarPonto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            // Parse das datas e horas inseridas pelo usuário
            LocalDateTime horaEntrada = LocalDateTime.parse(txtHoraEntrada.getText(), formatter);
            LocalDateTime horaSaida = LocalDateTime.parse(txtHoraSaida.getText(), formatter);

            // Validação para garantir que a hora de saída não é anterior à hora de entrada
            if (horaSaida.isBefore(horaEntrada)) {
                JOptionPane.showMessageDialog(this, "Hora de saída não pode ser anterior à hora de entrada.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cria um novo objeto PontoEletrico e popula os dados
            PontoEletrico ponto = new PontoEletrico();
            ponto.setFuncionario(funcionario);  // Associa o ponto ao funcionário logado
            ponto.setHoraEntrada(horaEntrada);  // Define a hora de entrada
            ponto.setHoraSaida(horaSaida);      // Define a hora de saída
            ponto.setDataRegistro(LocalDateTime.now());  // Registra o momento atual como a data de registro

            // Chama o método do controller para persistir o ponto no banco de dados
            pontoEletricoController.registrarPonto(ponto);

            // Exibe uma mensagem de sucesso e limpa os campos de entrada
            JOptionPane.showMessageDialog(this, "Ponto registrado com sucesso! ID do ponto: " + ponto.getId(),
                    "Registro de Ponto", JOptionPane.INFORMATION_MESSAGE);
            txtHoraEntrada.setText("");
            txtHoraSaida.setText("");

        } catch (DateTimeParseException e) {
            // Tratamento de erro para formato de data/hora inválido
            JOptionPane.showMessageDialog(this, "Formato de data/hora inválido. Use o formato: yyyy-MM-ddTHH:mm",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (javax.persistence.PersistenceException e) {
            // Tratamento para erros relacionados à persistência no banco de dados
            JOptionPane.showMessageDialog(this,
                    "Erro ao registrar ponto: Problema de conexão com o banco de dados. Por favor, tente novamente mais tarde.",
                    "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Tratamento genérico para qualquer outro erro
            JOptionPane.showMessageDialog(this, "Erro ao registrar ponto: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para trocar para a tela de consulta de pontos
    private void abrirConsultaPonto() {
        janelaPrincipal.trocarPainel("consultaPonto");  // Usa a instância de JanelaPrincipal para mudar de tela
    }

}
