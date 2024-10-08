package com.anthony.br.Views;

import javax.swing.*;
import com.anthony.br.Controllers.FuncionarioController;
import com.anthony.br.Models.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class FuncionarioForm extends JPanel {
    private JTextField nomeField, cpfField, enderecoField, telefoneField, cargoField, departamentoField, salarioField,
            dataContratacaoField, dataNascimentoField, emailField;
    private JButton cadastrarButton;
    private FuncionarioTable funcionarioTable;

    // Construtor que aceita uma instância de FuncionarioTable
    public FuncionarioForm(FuncionarioTable funcionarioTable) {
        this.funcionarioTable = funcionarioTable;

        setLayout(new GridBagLayout()); // Usando GridBagLayout para melhor controle do layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes

        // Configuração dos componentes
        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblNome, gbc);
        nomeField = new JTextField(20);
        gbc.gridx = 1;
        add(nomeField, gbc);

        JLabel lblCpf = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblCpf, gbc);
        cpfField = new JTextField(20);
        gbc.gridx = 1;
        add(cpfField, gbc);

        JLabel lblEndereco = new JLabel("Endereço:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEndereco, gbc);
        enderecoField = new JTextField(20);
        gbc.gridx = 1;
        add(enderecoField, gbc);

        JLabel lblTelefone = new JLabel("Telefone:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblTelefone, gbc);
        telefoneField = new JTextField(20);
        gbc.gridx = 1;
        add(telefoneField, gbc);

        JLabel lblCargo = new JLabel("Cargo:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblCargo, gbc);
        cargoField = new JTextField(20);
        gbc.gridx = 1;
        add(cargoField, gbc);

        JLabel lblDepartamento = new JLabel("Departamento:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblDepartamento, gbc);
        departamentoField = new JTextField(20);
        gbc.gridx = 1;
        add(departamentoField, gbc);

        JLabel lblSalario = new JLabel("Salário:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblSalario, gbc);
        salarioField = new JTextField(20);
        gbc.gridx = 1;
        add(salarioField, gbc);

        JLabel lblDataContratacao = new JLabel("Data de Contratação:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblDataContratacao, gbc);
        dataContratacaoField = new JTextField(20);
        gbc.gridx = 1;
        add(dataContratacaoField, gbc);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblDataNascimento, gbc);
        dataNascimentoField = new JTextField(20);
        gbc.gridx = 1;
        add(dataNascimentoField, gbc);

        JLabel lblEmail = new JLabel("Email:"); // Campo de email
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(lblEmail, gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        cadastrarButton = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        add(cadastrarButton, gbc);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
    }

    private void cadastrarFuncionario() {
        // Captura os valores dos campos
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String cargo = cargoField.getText();
        String departamento = departamentoField.getText();
        String email = emailField.getText();
        double salario;
        LocalDate dataContratacao;
        LocalDate dataNascimento;

        // Validação dos campos
        if (!validarCampos()) {
            JOptionPane.showMessageDialog(FuncionarioForm.this, "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            salario = Double.parseDouble(salarioField.getText());
            dataContratacao = LocalDate.parse(dataContratacaoField.getText());
            dataNascimento = LocalDate.parse(dataNascimentoField.getText());

            // Criar instância do Funcionario
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario, dataContratacao, departamento,
                    dataNascimento, email, endereco, telefone, "", null, null);

            // Chamar o controller para salvar o funcionário
            FuncionarioController funcionarioController = new FuncionarioController();
            funcionarioController.cadastrarFuncionario(funcionario);

            JOptionPane.showMessageDialog(FuncionarioForm.this, "Funcionário cadastrado com sucesso!");

            // Atualiza a tabela após o cadastro
            funcionarioTable.atualizarTabela();

            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(FuncionarioForm.this, "O salário deve ser um número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(FuncionarioForm.this, "Erro ao cadastrar o funcionário: " + ex.getMessage());
        }
    }

    private boolean validarCampos() {
        // Validação simples para verificar se algum campo está vazio
        return !nomeField.getText().isEmpty() &&
                !cpfField.getText().isEmpty() &&
                !enderecoField.getText().isEmpty() &&
                !telefoneField.getText().isEmpty() &&
                !cargoField.getText().isEmpty() &&
                !departamentoField.getText().isEmpty() &&
                !salarioField.getText().isEmpty() &&
                !dataContratacaoField.getText().isEmpty() &&
                !dataNascimentoField.getText().isEmpty() &&
                !emailField.getText().isEmpty();
    }

    private void limparCampos() {
        // Limpa todos os campos do formulário
        nomeField.setText("");
        cpfField.setText("");
        enderecoField.setText("");
        telefoneField.setText("");
        cargoField.setText("");
        departamentoField.setText("");
        salarioField.setText("");
        dataContratacaoField.setText("");
        dataNascimentoField.setText("");
        emailField.setText("");
    }
}
