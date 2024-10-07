package com.anthony.br.Models;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FolhaPagamentoExporter {
    // Método para exportar folha de pagamento para PDF
    public void exportarParaPDF(FolhaPagamento folhaPagamento, String caminhoArquivo) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open();

            adicionarInformacoesFolha(document, folhaPagamento); // Organiza as informações no PDF

            document.close();
            System.out.println("PDF gerado com sucesso no caminho: " + caminhoArquivo);
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado ou caminho inválido.");
            e.printStackTrace();
        } catch (DocumentException e) {
            System.err.println("Erro ao gerar o documento PDF.");
            e.printStackTrace();
        }
    }

    // Método privado para adicionar as informações da folha de pagamento ao PDF
    private void adicionarInformacoesFolha(Document document, FolhaPagamento folhaPagamento) throws DocumentException {
        document.add(new Paragraph("Folha de Pagamento"));
        document.add(new Paragraph("-----------------------------------------------------"));
        document.add(new Paragraph("Funcionário: " + folhaPagamento.getFuncionario().getNome()));
        document.add(new Paragraph("Mês de Referência: " + folhaPagamento.getMesReferencia()));
        document.add(new Paragraph("Salário Bruto: R$ " + String.format("%.2f", folhaPagamento.getSalarioBruto())));
        document.add(new Paragraph("INSS: R$ " + String.format("%.2f", folhaPagamento.getInss())));
        document.add(new Paragraph("FGTS: R$ " + String.format("%.2f", folhaPagamento.getFgts())));
        document.add(
                new Paragraph("Total de Benefícios: R$ " + String.format("%.2f", folhaPagamento.getTotalBeneficios())));
        document.add(new Paragraph("Salário Líquido: R$ " + String.format("%.2f", folhaPagamento.getSalarioLiquido())));
        document.add(new Paragraph("-----------------------------------------------------"));
        document.add(new Paragraph("Gerado automaticamente pelo sistema de Gestão de RH."));
    }
}
