





//     //(em progresso)
// // package com.anthony.br.Views;

// // Importando as classes necessárias para a construção da interface
// import java.awt.BorderLayout;
// import java.awt.*; // Importa todas as classes do pacote awt
// import java.time.LocalDate; // Para manipulação de datas
// import java.util.List; // Para uso de listas

// import javax.swing.*; // Importa classes do pacote Swing
// import com.anthony.br.Models.FolhaPagamento; // Importa a classe FolhaPagamento
// import com.anthony.br.Models.Funcionario; // Importa a classe Funcionario

// // Classe que representa a interface de geração de folha de pagamento
// public class FolhaPagamentoView extends JFrame {
//     // Componentes da interface
//     private JComboBox<Funcionario> funcionarioComboBox; // ComboBox para selecionar o funcionário
//     private JComboBox<String> mesReferenciaComboBox; // ComboBox para selecionar o mês de referência
//     private JButton gerarFolhaButton; // Botão para gerar a folha de pagamento
//     private JButton exportarPDFButton; // Botão para exportar a folha para PDF
//     private JTextArea resultadoArea; // Área de texto para exibir os resultados

//     // Construtor da classe que recebe uma lista de funcionários
//     public FolhaPagamentoView(List<Funcionario> funcionarios) {
//         setTitle("Geração de Folha de Pagamento"); // Define o título da janela
//         setSize(600, 400); // Define o tamanho da janela
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
//         setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

//         // Painel de seleção de funcionário e mês
//         JPanel selectionPanel = new JPanel();
//         selectionPanel.setLayout(new GridLayout(2, 2)); // Define o layout do painel como GridLayout

//         // ComboBox para selecionar o funcionário
//         funcionarioComboBox = new JComboBox<>();
//         for (Funcionario funcionario : funcionarios) {
//             funcionarioComboBox.addItem(funcionario); // Adiciona cada funcionário à ComboBox
//         }
//         selectionPanel.add(new JLabel("Selecione o Funcionário:")); // Adiciona um rótulo
//         selectionPanel.add(funcionarioComboBox); // Adiciona a ComboBox de funcionários ao painel

//         // ComboBox para selecionar o mês de referência
//         mesReferenciaComboBox = new JComboBox<>(getMeses()); // Inicializa a ComboBox com os meses
//         selectionPanel.add(new JLabel("Selecione o Mês de Referência:")); // Adiciona um rótulo
//         selectionPanel.add(mesReferenciaComboBox); // Adiciona a ComboBox de meses ao painel

//         // Painel de botões
//         JPanel buttonPanel = new JPanel();
//         gerarFolhaButton = new JButton("Gerar Folha"); // Inicializa o botão de gerar folha
//         exportarPDFButton = new JButton("Exportar para PDF"); // Inicializa o botão de exportar para PDF

//         buttonPanel.add(gerarFolhaButton); // Adiciona o botão de gerar folha ao painel
//         buttonPanel.add(exportarPDFButton); // Adiciona o botão de exportar ao painel

//         // Área de exibição dos resultados
//         resultadoArea = new JTextArea(10, 50); // Cria uma área de texto com 10 linhas e 50 colunas
//         resultadoArea.setEditable(false); // Define a área de texto como não editável
//         JScrollPane scrollPane = new JScrollPane(resultadoArea); // Cria um JScrollPane para a área de texto

//         // Adiciona os componentes à janela
//         add(selectionPanel, BorderLayout.NORTH); // Adiciona o painel de seleção ao topo da janela
//         add(buttonPanel, BorderLayout.CENTER); // Adiciona o painel de botões ao centro da janela
//         add(scrollPane, BorderLayout.SOUTH); // Adiciona o painel de rolagem da área de texto ao fundo da janela

//         // Ações dos botões
//         gerarFolhaButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 gerarFolhaDePagamento(); // Chama o método para gerar a folha ao clicar no botão
//             }
//         });

//         exportarPDFButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 exportarParaPDF(); // Chama o método para exportar para PDF ao clicar no botão
//             }
//         });
//     }

//     // Método que retorna os meses do ano para o ComboBox de referência
//     private String[] getMeses() {
//         return new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
//     }

//     // Método que gera a folha de pagamento
//     private void gerarFolhaDePagamento() {
//         // Obtém o funcionário e o mês selecionado
//         Funcionario funcionario = (Funcionario) funcionarioComboBox.getSelectedItem(); // Obtém o funcionário selecionado
//         String mesSelecionado = (String) mesReferenciaComboBox.getSelectedItem(); // Obtém o mês selecionado
//         LocalDate mesReferencia = LocalDate.now().withMonth(mesParaNumero(mesSelecionado)); // Obtém a data do mês de referência

//         // Gera a folha de pagamento (exemplo de dados de ponto)
//         try {
//             FolhaPagamento folha = new FolhaPagamento(funcionario, mesReferencia, funcionario.getRegistrosDePonto()); // Cria uma nova folha de pagamento

//             // Exibe os resultados na área de texto
//             resultadoArea.setText("Folha de Pagamento de " + funcionario.getNome() + ":\n");
//             resultadoArea.append("Mês: " + mesSelecionado + "\n");
//             resultadoArea.append("Salário Bruto: " + folha.getSalarioBruto() + "\n");
//             resultadoArea.append("INSS: " + folha.getInss() + "\n");
//             resultadoArea.append("FGTS: " + folha.getFgts() + "\n");
//             resultadoArea.append("Benefícios: " + folha.getTotalBeneficios() + "\n");
//             resultadoArea.append("Salário Líquido: " + folha.getSalarioLiquido() + "\n");

//         } catch (Exception ex) {
//             // Em caso de erro, exibe uma mensagem de erro
//             JOptionPane.showMessageDialog(this, "Erro ao gerar folha de pagamento: " + ex.getMessage());
//         }
//     }

//     // Método que converte o nome do mês para o número correspondente
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
//                 return 1; // Retorna 1 como padrão caso o mês não seja reconhecido
//         }
//     }

//     // Método para exportar os dados para PDF (exemplo de implementação futura)
//     private void exportarParaPDF() {
//         JOptionPane.showMessageDialog(this, "Exportação para PDF ainda não implementada."); // Mensagem informando que a funcionalidade ainda não está implementada
//     }

// // }
