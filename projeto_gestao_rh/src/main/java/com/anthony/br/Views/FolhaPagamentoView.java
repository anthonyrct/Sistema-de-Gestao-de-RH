





    //(em progresso)
// package com.anthony.br.Views;

// import java.awt.BorderLayout;
// import java.awt.*;
// import java.time.LocalDate;
// import java.util.List;

// import javax.faces.event.ActionEvent;
// import javax.faces.event.ActionListener;
// import javax.swing.*;

// import com.anthony.br.Models.FolhaPagamento;
// import com.anthony.br.Models.Funcionario;

// public class FolhaPagamentoView extends JFrame{
//      private JComboBox<Funcionario> funcionarioComboBox;
//     private JComboBox<String> mesReferenciaComboBox;
//     private JButton gerarFolhaButton;
//     private JButton exportarPDFButton;
//     private JTextArea resultadoArea;

//     public FolhaPagamentoView(List<Funcionario> funcionarios) {
//         setTitle("Geração de Folha de Pagamento");
//         setSize(600, 400);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         // Painel de seleção de funcionário e mês
//         JPanel selectionPanel = new JPanel();
//         selectionPanel.setLayout(new GridLayout(2, 2));

//         // ComboBox para selecionar o funcionário
//         funcionarioComboBox = new JComboBox<>();
//         for (Funcionario funcionario : funcionarios) {
//             funcionarioComboBox.addItem(funcionario);
//         }
//         selectionPanel.add(new JLabel("Selecione o Funcionário:"));
//         selectionPanel.add(funcionarioComboBox);

//         // ComboBox para selecionar o mês de referência
//         mesReferenciaComboBox = new JComboBox<>(getMeses());
//         selectionPanel.add(new JLabel("Selecione o Mês de Referência:"));
//         selectionPanel.add(mesReferenciaComboBox);

//         // Painel de botões
//         JPanel buttonPanel = new JPanel();
//         gerarFolhaButton = new JButton("Gerar Folha");
//         exportarPDFButton = new JButton("Exportar para PDF");

//         buttonPanel.add(gerarFolhaButton);
//         buttonPanel.add(exportarPDFButton);

//         // Área de exibição dos resultados
//         resultadoArea = new JTextArea(10, 50);
//         resultadoArea.setEditable(false);
//         JScrollPane scrollPane = new JScrollPane(resultadoArea);

//         // Adiciona os componentes à janela
//         add(selectionPanel, BorderLayout.NORTH);
//         add(buttonPanel, BorderLayout.CENTER);
//         add(scrollPane, BorderLayout.SOUTH);

//         // Ações dos botões
//         gerarFolhaButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 gerarFolhaDePagamento();
//             }
//         });

//         exportarPDFButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 exportarParaPDF();
//             }
//         });
//     }

//     private String[] getMeses() {
//         // Retorna os meses para o ComboBox de referência
//         return new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
//     }

//     private void gerarFolhaDePagamento() {
//         // Obtém o funcionário e o mês selecionado
//         Funcionario funcionario = (Funcionario) funcionarioComboBox.getSelectedItem();
//         String mesSelecionado = (String) mesReferenciaComboBox.getSelectedItem();
//         LocalDate mesReferencia = LocalDate.now().withMonth(mesParaNumero(mesSelecionado));

//         // Gera a folha de pagamento (exemplo de dados de ponto)
//         try {
//             FolhaPagamento folha = new FolhaPagamento(funcionario, mesReferencia, funcionario.getRegistrosDePonto());

//             // Exibe os resultados na área de texto
//             resultadoArea.setText("Folha de Pagamento de " + funcionario.getNome() + ":\n");
//             resultadoArea.append("Mês: " + mesSelecionado + "\n");
//             resultadoArea.append("Salário Bruto: " + folha.getSalarioBruto() + "\n");
//             resultadoArea.append("INSS: " + folha.getInss() + "\n");
//             resultadoArea.append("FGTS: " + folha.getFgts() + "\n");
//             resultadoArea.append("Benefícios: " + folha.getTotalBeneficios() + "\n");
//             resultadoArea.append("Salário Líquido: " + folha.getSalarioLiquido() + "\n");

//         } catch (Exception ex) {
//             JOptionPane.showMessageDialog(this, "Erro ao gerar folha de pagamento: " + ex.getMessage());
//         }
//     }

//     private int mesParaNumero(String mes) {
//         switch (mes) {
//             case "Janeiro":
//                 return 1;
//             case "Fevereiro":
//                 return 2;
//             case "Março":
//                 return 3;
//             case "Abril":
//                 return 4;
//             case "Maio":
//                 return 5;
//             case "Junho":
//                 return 6;
//             case "Julho":
//                 return 7;
//             case "Agosto":
//                 return 8;
//             case "Setembro":
//                 return 9;
//             case "Outubro":
//                 return 10;
//             case "Novembro":
//                 return 11;
//             case "Dezembro":
//                 return 12;
//             default:
//                 return 1;
//         }
//     }

//     private void exportarParaPDF() {
//         // Função para exportar os dados para PDF (exemplo de implementação futura)
//         JOptionPane.showMessageDialog(this, "Exportação para PDF ainda não implementada.");
//     }

// }
