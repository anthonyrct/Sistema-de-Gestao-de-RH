package com.anthony.br.Views;

import javax.swing.*;

import com.anthony.br.Models.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame {

      private JPanel mainPanel;
    private FuncionarioForm funcionarioForm;
    private FuncionarioTable funcionarioTable;
    private ConsultaPontoView consultaPontoView;
    private RegistroPontoView registroPontoView;

    public JanelaPrincipal(Funcionario funcionarioLogado) {
        setTitle("Sistema de Gestão de Recursos Humanos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra de Menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu "Funcionários"
        JMenu menuFuncionarios = new JMenu("Funcionários");
        menuBar.add(menuFuncionarios);

        // Item do menu para cadastrar funcionários
        JMenuItem menuItemCadastrar = new JMenuItem("Cadastrar Funcionário");
        menuFuncionarios.add(menuItemCadastrar);

        // Item do menu para visualizar a tabela de funcionários
        JMenuItem menuItemListar = new JMenuItem("Listar Funcionários");
        menuFuncionarios.add(menuItemListar);

        // Menu "Pontos Eletrônicos"
        JMenu menuPontos = new JMenu("Pontos Eletrônicos");
        menuBar.add(menuPontos);

        // Item do menu para consultar pontos eletrônicos
        JMenuItem menuItemConsultarPonto = new JMenuItem("Consultar Ponto");
        menuPontos.add(menuItemConsultarPonto);

        // Painel principal
        mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Instanciando os painéis
        funcionarioTable = new FuncionarioTable();
        funcionarioForm = new FuncionarioForm(funcionarioTable);
        consultaPontoView = new ConsultaPontoView(funcionarioLogado);
        registroPontoView = new RegistroPontoView(funcionarioLogado, this); // Passando o MainFrame

        // Adicionando os painéis ao CardLayout
        mainPanel.add(funcionarioForm, "form");
        mainPanel.add(funcionarioTable, "table");
        mainPanel.add(consultaPontoView, "consultaPonto");
        mainPanel.add(registroPontoView, "registroPonto");

        // Ação do menu para trocar para o painel de cadastro de funcionário
        menuItemCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarPainel("form");
            }
        });

        // Ação do menu para trocar para o painel de listagem de funcionários
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarPainel("table");
                funcionarioTable.atualizarTabela(); // Atualiza a tabela ao exibir
            }
        });

        // Ação do menu para abrir a tela de consulta de pontos
        menuItemConsultarPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConsultaPonto();
            }
        });

        // Exibe o painel inicial
        trocarPainel("table");
    }

    // Método para trocar de painel
    public void trocarPainel(String nomePainel) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, nomePainel);
    }

    // Método para abrir a tela de consulta de pontos
    public void abrirConsultaPonto() {
        trocarPainel("consultaPonto");
        consultaPontoView.consultarPontos(); // Opcional: consulta automática ao abrir a tela
    }

}
