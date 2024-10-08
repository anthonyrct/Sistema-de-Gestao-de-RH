package com.anthony.br;

import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

import com.anthony.br.Models.Beneficio;
import com.anthony.br.Models.Funcionario;
import com.anthony.br.Views.JanelaPrincipal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Executar a interface gráfica no thread de eventos
        SwingUtilities.invokeLater(() -> {
            // Criar um Funcionario de exemplo (ou obter um funcionário logado de um banco
            // de dados)
            List<Beneficio> beneficios = new ArrayList<>(); // Supondo que você tenha uma classe Beneficio
            Funcionario funcionarioLogado = new Funcionario(
                    "João da Silva",
                    "123.456.789-00", // CPF exemplo
                    "Desenvolvedor",
                    5000.00,
                    LocalDate.of(2022, 5, 15), // Data de contratação exemplo
                    "Desenvolvimento",
                    LocalDate.of(1990, 4, 20), // Data de nascimento exemplo
                    "joao@empresa.com", // Email exemplo
                    "Rua Exemplo, 123", // Endereço exemplo
                    "(11) 91234-5678", // Telefone exemplo
                    "1234-5678", // Conta bancária exemplo
                    beneficios, // Lista de benefícios
                    new ArrayList<>());

            // Cria uma instância da janela principal com o funcionário logado
            JanelaPrincipal janelaPrincipal = new JanelaPrincipal(funcionarioLogado);
            janelaPrincipal.setVisible(true); // Torna a janela visível
        });
    }
}