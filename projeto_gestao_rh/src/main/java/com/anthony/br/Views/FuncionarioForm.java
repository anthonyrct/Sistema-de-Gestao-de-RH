package com.anthony.br.Views;

import javax.swing.*; // Importação de componentes Swing para a interface gráfica
import com.anthony.br.Controllers.FuncionarioController; // Importação do controlador de funcionário
import com.anthony.br.Models.Funcionario; // Importação do modelo de funcionário

import java.awt.*; // Importação das classes de layout
import java.awt.event.ActionEvent; // Importação para manipulação de eventos
import java.awt.event.ActionListener; // Importação para o listener de eventos
import java.time.LocalDate; // Importação para manipulação de datas

// Classe que representa o formulário para cadastro de funcionários
public class FuncionarioForm extends JPanel {
    // Declaração dos campos de entrada
    private JTextField nomeField, cpfField, enderecoField, telefoneField, cargoField, departamentoField, salarioField,
            dataContratacaoField, dataNascimentoField, emailField;
    private JButton cadastrarButton; // Botão para cadastrar funcionário
    private FuncionarioTable funcionarioTable; // Referência à tabela de funcionários

    // Construtor que aceita uma instância de FuncionarioTable
    public FuncionarioForm(FuncionarioTable funcionarioTable) {
        this.funcionarioTable = funcionarioTable; // Inicializa a referência da tabela

        // Configuração do layout utilizando GridBagLayout para controle flexível
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Componentes ocupam toda a largura
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes

        // Configuração dos componentes do formulário
        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0; // Posição x
        gbc.gridy = 0; // Posição y
        add(lblNome, gbc); // Adiciona o rótulo ao formulário
        nomeField = new JTextField(20); // Campo de texto para o nome
        gbc.gridx = 1; // Posição x do campo
        add(nomeField, gbc); // Adiciona o campo ao formulário

        // Repetição do processo para os demais campos
        JLabel lblCpf = new JLabel("CPF:");
        gbc.gridx = 0; gbc.gridy = 1; // Atualiza a posição
        add(lblCpf, gbc);
        cpfField = new JTextField(20);
        gbc.gridx = 1;
        add(cpfField, gbc);

        JLabel lblEndereco = new JLabel("Endereço:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblEndereco, gbc);
        enderecoField = new JTextField(20);
        gbc.gridx = 1;
        add(enderecoField, gbc);

        JLabel lblTelefone = new JLabel("Telefone:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblTelefone, gbc);
        telefoneField = new JTextField(20);
        gbc.gridx = 1;
        add(telefoneField, gbc);

        JLabel lblCargo = new JLabel("Cargo:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(lblCargo, gbc);
        cargoField = new JTextField(20);
        gbc.gridx = 1;
        add(cargoField, gbc);

        JLabel lblDepartamento = new JLabel("Departamento:");
        gbc.gridx = 0; gbc.gridy = 5;
        add(lblDepartamento, gbc);
        departamentoField = new JTextField(20);
        gbc.gridx = 1;
        add(departamentoField, gbc);

        JLabel lblSalario = new JLabel("Salário:");
        gbc.gridx = 0; gbc.gridy = 6;
        add(lblSalario, gbc);
        salarioField = new JTextField(20);
        gbc.gridx = 1;
        add(salarioField, gbc);

        JLabel lblDataContratacao = new JLabel("Data de Contratação:");
        gbc.gridx = 0; gbc.gridy = 7;
        add(lblDataContratacao, gbc);
        dataContratacaoField = new JTextField(20);
        gbc.gridx = 1;
        add(dataContratacaoField, gbc);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        gbc.gridx = 0; gbc.gridy = 8;
        add(lblDataNascimento, gbc);
        dataNascimentoField = new JTextField(20);
        gbc.gridx = 1;
        add(dataNascimentoField, gbc);

        JLabel lblEmail = new JLabel("Email:"); // Campo de email
        gbc.gridx = 0; gbc.gridy = 9;
        add(lblEmail, gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Botão para cadastrar o funcionário
        cadastrarButton = new JButton("Cadastrar");
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        add(cadastrarButton, gbc);

        // Adiciona um ActionListener ao botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario(); // Chama o método para cadastrar o funcionário ao clicar no botão
            }
        });
    }

    // Método que realiza o cadastro do funcionário
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
            // Exibe mensagem de erro se algum campo estiver vazio
            JOptionPane.showMessageDialog(FuncionarioForm.this, "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            // Converte os campos para os tipos apropriados
            salario = Double.parseDouble(salarioField.getText());
            dataContratacao = LocalDate.parse(dataContratacaoField.getText());
            dataNascimento = LocalDate.parse(dataNascimentoField.getText());

            // Cria uma instância do Funcionario
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario, dataContratacao, departamento,
                    dataNascimento, email, endereco, telefone, "", null, null);

            // Chama o controller para salvar o funcionário
            FuncionarioController funcionarioController = new FuncionarioController();
            funcionarioController.cadastrarFuncionario(funcionario);

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(FuncionarioForm.this, "Funcionário cadastrado com sucesso!");

            // Atualiza a tabela após o cadastro
            funcionarioTable.atualizarTabela();

            // Limpa os campos do formulário
            limparCampos();
        } catch (NumberFormatException ex) {
            // Mensagem de erro se o salário não for um número válido
            JOptionPane.showMessageDialog(FuncionarioForm.this, "O salário deve ser um número válido.");
        } catch (Exception ex) {
            // Mensagem de erro genérica
            JOptionPane.showMessageDialog(FuncionarioForm.this, "Erro ao cadastrar o funcionário: " + ex.getMessage());
        }
    }

    // Método que valida os campos do formulário
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

    // Método que limpa todos os campos do formulário
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
        emailField.setText("");
    }
}
