package com.anthony.br;

// Importa classes necessárias
import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

import com.anthony.br.Models.Beneficio; // Importa a classe Beneficio
import com.anthony.br.Models.Funcionario; // Importa a classe Funcionario
import com.anthony.br.Views.JanelaPrincipal; // Importa a classe JanelaPrincipal
import java.time.LocalDate; // Importa a classe LocalDate para manipulação de datas

public class Main {
    public static void main(String[] args) {
        // Executar a interface gráfica no thread de eventos
        SwingUtilities.invokeLater(() -> {
            // Criar um Funcionario de exemplo (ou obter um funcionário logado de um banco
            // de dados)
            List<Beneficio> beneficios = new ArrayList<>(); // Lista para armazenar benefícios
            Funcionario funcionarioLogado = new Funcionario(
                    "João da Silva", // Nome do funcionário
                    "123.456.789-00", // CPF do funcionário
                    "Desenvolvedor", // Cargo do funcionário
                    5000.00, // Salário do funcionário
                    LocalDate.of(2022, 5, 15), // Data de contratação do funcionário
                    "Desenvolvimento", // Departamento do funcionário
                    LocalDate.of(1990, 4, 20), // Data de nascimento do funcionário
                    "joao@empresa.com", // Email do funcionário
                    "Rua Exemplo, 123", // Endereço do funcionário
                    "(11) 91234-5678", // Telefone do funcionário
                    "1234-5678", // Conta bancária do funcionário
                    beneficios, // Lista de benefícios do funcionário
                    new ArrayList<>()); // Lista de registros de ponto do funcionário

            // Cria uma instância da janela principal com o funcionário logado
            JanelaPrincipal janelaPrincipal = new JanelaPrincipal(funcionarioLogado);
            janelaPrincipal.setVisible(true); // Torna a janela visível
        });
    }
}