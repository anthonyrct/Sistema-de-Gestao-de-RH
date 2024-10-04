package main.java.com.anthony.br.Funcionario;

import javax.swing.*;

import main.java.com.anthony.br.Controllers.FuncionarioController;
import main.java.com.anthony.br.Models.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioForm extends JPanel {
    private JTextField nomeField, cpfField, enderecoField, telefoneField, cargoField, departamentoField, salarioField;
    private JButton salvarButton;

    public FuncionarioForm() {
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

        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para salvar o funcionário
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String telefone = telefoneField.getText();
                String cargo = cargoField.getText();
                String departamento = departamentoField.getText();
                double salario = Double.parseDouble(salarioField.getText());

                // Validação básica
                if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(FuncionarioForm.this, "Todos os campos devem ser preenchidos.");
                    return;
                }

                // Criar uma instância do Funcionario com os dados do formulário
                Funcionario funcionario = new Funcionario(nome, cpf, endereco, telefone, cargo, departamento, salario);

                // Chamar o controller para salvar o funcionário
                try {
                    FuncionarioController funcionarioController = new FuncionarioController();
                    funcionarioController.salvarFuncionario(funcionario);
                    JOptionPane.showMessageDialog(FuncionarioForm.this, "Funcionário salvo com sucesso!");
                    limparCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FuncionarioForm.this,
                            "Erro ao salvar o funcionário: " + ex.getMessage());
                }
            }

            private void limparCampos() {
                nomeField.setText("");
                cpfField.setText("");
                enderecoField.setText("");
                telefoneField.setText("");
                cargoField.setText("");
                departamentoField.setText("");
                salarioField.setText("");
            }
        });
        add(salvarButton);
    }
}
