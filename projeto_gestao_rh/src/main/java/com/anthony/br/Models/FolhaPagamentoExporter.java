package com.anthony.br.Models;

import com.itextpdf.text.Document; // Importa a classe Document para criar o PDF
import com.itextpdf.text.DocumentException; // Importa a exceção para tratamento de erros de documento
import com.itextpdf.text.Paragraph; // Importa a classe Paragraph para adicionar parágrafos ao PDF
import com.itextpdf.text.pdf.PdfWriter; // Importa a classe PdfWriter para escrever no PDF

import java.io.FileNotFoundException; // Importa a exceção para tratamento de erros de arquivo não encontrado
import java.io.FileOutputStream; // Importa a classe FileOutputStream para escrever em arquivos

public class FolhaPagamentoExporter {
    // Método para exportar folha de pagamento para PDF
    public void exportarParaPDF(FolhaPagamento folhaPagamento, String caminhoArquivo) {
        Document document = new Document(); // Cria um novo documento PDF
        try {
            // Cria um PdfWriter que escreve no documento e no caminho especificado
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open(); // Abre o documento para escrita

            // Adiciona as informações da folha de pagamento ao PDF
            adicionarInformacoesFolha(document, folhaPagamento);

            document.close(); // Fecha o documento após a adição de informações
            System.out.println("PDF gerado com sucesso no caminho: " + caminhoArquivo); // Mensagem de sucesso
        } catch (FileNotFoundException e) {
            // Trata a exceção quando o arquivo não é encontrado
            System.err.println("Erro: Arquivo não encontrado ou caminho inválido.");
            e.printStackTrace(); // Exibe o rastreamento do erro
        } catch (DocumentException e) {
            // Trata a exceção quando ocorre um erro ao gerar o documento PDF
            System.err.println("Erro ao gerar o documento PDF.");
            e.printStackTrace(); // Exibe o rastreamento do erro
        }
    }

    // Método privado para adicionar as informações da folha de pagamento ao PDF
    private void adicionarInformacoesFolha(Document document, FolhaPagamento folhaPagamento) throws DocumentException {
        // Adiciona o título e as informações da folha de pagamento no documento
        document.add(new Paragraph("Folha de Pagamento"));
        document.add(new Paragraph("-----------------------------------------------------")); // Linha separadora
        document.add(new Paragraph("Funcionário: " + folhaPagamento.getFuncionario().getNome())); // Nome do funcionário
        document.add(new Paragraph("Mês de Referência: " + folhaPagamento.getMesReferencia())); // Mês de referência
        document.add(new Paragraph("Salário Bruto: R$ " + String.format("%.2f", folhaPagamento.getSalarioBruto()))); // Salário bruto formatado
        document.add(new Paragraph("INSS: R$ " + String.format("%.2f", folhaPagamento.getInss()))); // Valor do INSS formatado
        document.add(new Paragraph("FGTS: R$ " + String.format("%.2f", folhaPagamento.getFgts()))); // Valor do FGTS formatado
        document.add(
                new Paragraph("Total de Benefícios: R$ " + String.format("%.2f", folhaPagamento.getTotalBeneficios()))); // Total de benefícios formatado
        document.add(new Paragraph("Salário Líquido: R$ " + String.format("%.2f", folhaPagamento.getSalarioLiquido()))); // Salário líquido formatado
        document.add(new Paragraph("-----------------------------------------------------")); // Linha separadora
        document.add(new Paragraph("Gerado automaticamente pelo sistema de Gestão de RH.")); // Mensagem de geração automática
    }
}
