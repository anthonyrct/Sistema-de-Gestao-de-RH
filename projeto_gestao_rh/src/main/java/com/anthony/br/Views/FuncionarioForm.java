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
            dataContratacaoField, dataNascimentoField, emailField; // Adicionar emailField
    private JButton cadastrarButton;
    private FuncionarioTable funcionarioTable; // Referência à tabela de funcionários

    // Construtor que aceita uma instância de FuncionarioTable
    public FuncionarioForm(FuncionarioTable funcionarioTable) {
        this.funcionarioTable = funcionarioTable; // Armazena a referência

        setLayout(new GridLayout(0, 2));

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        add(enderecoField);

        add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        add(telefoneField);

        add(new JLabel("Cargo:"));
        cargoField = new JTextField();
        add(cargoField);

        add(new JLabel("Departamento:"));
        departamentoField = new JTextField();
        add(departamentoField);

        add(new JLabel("Salário:"));
        salarioField = new JTextField();
        add(salarioField);

        add(new JLabel("Data de Contratação:"));
        dataContratacaoField = new JTextField();
        add(dataContratacaoField);

        add(new JLabel("Data de Nascimento:"));
        dataNascimentoField = new JTextField();
        add(dataNascimentoField);

        add(new JLabel("Email:")); // Adicionar campo de email
        emailField = new JTextField();
        add(emailField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String telefone = telefoneField.getText();
                String cargo = cargoField.getText();
                String departamento = departamentoField.getText();
                String email = emailField.getText(); // Capturar email
                double salario;
                LocalDate dataContratacao;
                LocalDate dataNascimento;

                // Validação básica
                if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || telefone.isEmpty() ||
                        cargo.isEmpty() || departamento.isEmpty() || salarioField.getText().isEmpty() ||
                        dataContratacaoField.getText().isEmpty() || dataNascimentoField.getText().isEmpty()
                        || email.isEmpty()) {
                    JOptionPane.showMessageDialog(FuncionarioForm.this, "Todos os campos devem ser preenchidos.");
                    return;
                }

                try {
                    salario = Double.parseDouble(salarioField.getText());
                    dataContratacao = LocalDate.parse(dataContratacaoField.getText());
                    dataNascimento = LocalDate.parse(dataNascimentoField.getText());

                    // Criar uma instância do Funcionario com os dados do formulário
                    Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario, dataContratacao, departamento,
                            dataNascimento, email, endereco, telefone, "", null, null);

                    // Chamar o controller para salvar o funcionário
                    FuncionarioController funcionarioController = new FuncionarioController();
                    funcionarioController.cadastrarFuncionario(funcionario);
                    JOptionPane.showMessageDialog(FuncionarioForm.this, "Funcionário cadastrado com sucesso!");

                    // Atualiza a tabela após o cadastro
                    funcionarioTable.atualizarTabela(); // Chama o método para atualizar a tabela

                    limparCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FuncionarioForm.this, "O salário deve ser um número válido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FuncionarioForm.this,
                            "Erro ao cadastrar o funcionário: " + ex.getMessage());
                }
            }
        });
        add(cadastrarButton);
    }

    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        enderecoField.setText("");
        telefoneField.setText("");
        cargoField.setText("");
        departamentoField.setText("");
        salarioField.setText("");
        dataContratacaoField.setText("");
        dataNascimentoField.setText("");
        emailField.setText(""); // Limpar o campo de email também
    }
}
