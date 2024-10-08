package com.anthony.br.Views;

import javax.swing.*;
import com.anthony.br.Models.Funcionario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame {

    private JPanel mainPanel;  // Painel principal que conterá os diferentes views
    private FuncionarioForm funcionarioForm;  // Formulário para cadastrar/editar funcionários
    private FuncionarioTable funcionarioTable;  // Tabela de listagem de funcionários
    private ConsultaPontoView consultaPontoView;  // Tela para consulta de pontos eletrônicos
    private RegistroPontoView registroPontoView;  // Tela para registro de pontos eletrônicos

    // Construtor da JanelaPrincipal
    public JanelaPrincipal(Funcionario funcionarioLogado) {
        setTitle("Sistema de Gestão de Recursos Humanos");  // Define o título da janela
        setSize(800, 600);  // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Encerra o programa ao fechar a janela
        setLocationRelativeTo(null);  // Centraliza a janela na tela
        setLayout(new BorderLayout());  // Define o layout principal como BorderLayout

        // Barra de Menu
        JMenuBar menuBar = new JMenuBar();  // Cria a barra de menu
        setJMenuBar(menuBar);  // Adiciona a barra de menu ao JFrame

        // Menu "Funcionários"
        JMenu menuFuncionarios = new JMenu("Funcionários");  // Cria o menu "Funcionários"
        menuBar.add(menuFuncionarios);  // Adiciona o menu à barra de menu

        // Item do menu para cadastrar funcionários
        JMenuItem menuItemCadastrar = new JMenuItem("Cadastrar Funcionário");
        menuFuncionarios.add(menuItemCadastrar);  // Adiciona a opção de cadastro ao menu "Funcionários"

        // Item do menu para visualizar a tabela de funcionários
        JMenuItem menuItemListar = new JMenuItem("Listar Funcionários");
        menuFuncionarios.add(menuItemListar);  // Adiciona a opção de listagem ao menu "Funcionários"

        // Menu "Pontos Eletrônicos"
        JMenu menuPontos = new JMenu("Pontos Eletrônicos");  // Cria o menu "Pontos Eletrônicos"
        menuBar.add(menuPontos);  // Adiciona o menu à barra de menu

        // Item do menu para consultar pontos eletrônicos
        JMenuItem menuItemConsultarPonto = new JMenuItem("Consultar Ponto");
        menuPontos.add(menuItemConsultarPonto);  // Adiciona a opção de consulta ao menu "Pontos Eletrônicos"

        // Painel principal usando CardLayout para trocar entre diferentes views
        mainPanel = new JPanel(new CardLayout());  
        add(mainPanel, BorderLayout.CENTER);  // Adiciona o painel principal ao centro do JFrame

        // Instanciando os painéis
        funcionarioTable = new FuncionarioTable();  // Painel de listagem de funcionários
        funcionarioForm = new FuncionarioForm(funcionarioTable);  // Formulário de cadastro de funcionários
        consultaPontoView = new ConsultaPontoView(funcionarioLogado);  // Tela de consulta de pontos
        registroPontoView = new RegistroPontoView(funcionarioLogado, this);  // Tela de registro de pontos

        // Adiciona os painéis ao mainPanel, cada um identificado por uma chave (form, table, etc.)
        mainPanel.add(funcionarioForm, "form");
        mainPanel.add(funcionarioTable, "table");
        mainPanel.add(consultaPontoView, "consultaPonto");
        mainPanel.add(registroPontoView, "registroPonto");

        // Ação do menu para trocar para o painel de cadastro de funcionário
        menuItemCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarPainel("form");  // Troca para o painel de cadastro de funcionário
            }
        });

        // Ação do menu para trocar para o painel de listagem de funcionários
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarPainel("table");  // Troca para o painel de listagem de funcionários
                funcionarioTable.atualizarTabela();  // Atualiza os dados da tabela ao exibir
            }
        });

        // Ação do menu para abrir a tela de consulta de pontos eletrônicos
        menuItemConsultarPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConsultaPonto();  // Troca para o painel de consulta de pontos
            }
        });

        // Exibe o painel inicial (tabela de funcionários)
        trocarPainel("table");
    }

    // Método para trocar entre os painéis usando CardLayout
    public void trocarPainel(String nomePainel) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());  // Obtém o layout do mainPanel
        cl.show(mainPanel, nomePainel);  // Exibe o painel correspondente ao nome fornecido
    }

    // Método para abrir a tela de consulta de pontos
    public void abrirConsultaPonto() {
        trocarPainel("consultaPonto");  // Troca para o painel de consulta de pontos
        consultaPontoView.consultarPontos();  // Opcional: realiza a consulta automática ao abrir
    }

}
