package com.anthony.br.Models;

// Importando as anotações necessárias para a JPA
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

// Definindo a entidade JPA para a tabela "relatorios"
@Entity
@Table(name = "relatorios")
public class Relatorio {

    // Identificador único da entidade, gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de relatório, não pode ser nulo. Exemplos: desempenho, horas trabalhadas, etc.
    @Column(name = "tipo_relatorio", nullable = false)
    private String tipoRelatorio; 

    // Conteúdo do relatório, não pode ser nulo
    @Column(nullable = false)
    private String conteudo;

    // Data de geração do relatório, não pode ser nula. Usando LocalDateTime ao invés de Date
    @Column(name = "data_geracao", nullable = false)
    private LocalDateTime dataGeracao; 

    // Construtor padrão
    public Relatorio() {
    }

    // Construtor que recebe todos os parâmetros necessários
    public Relatorio(String tipoRelatorio, String conteudo, LocalDateTime dataGeracao) {
        this.tipoRelatorio = tipoRelatorio;
        this.conteudo = conteudo;
        this.dataGeracao = dataGeracao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    // Não é necessário um setter para o id, pois ele é gerado pelo banco
    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    // Setter para o tipo de relatório com validação
    public void setTipoRelatorio(String tipoRelatorio) {
        if (tipoRelatorio == null || tipoRelatorio.isEmpty()) {
            throw new IllegalArgumentException("O tipo de relatório não pode ser vazio.");
        }
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getConteudo() {
        return conteudo;
    }

    // Setter para o conteúdo do relatório com validação
    public void setConteudo(String conteudo) {
        if (conteudo == null || conteudo.isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do relatório não pode ser vazio.");
        }
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    // Método toString para representação textual da classe
    @Override
    public String toString() {
        return "Relatorio{id=" + id + ", tipoRelatorio='" + tipoRelatorio + "', dataGeracao=" + dataGeracao + "}";
    }

    // Método equals para comparar duas instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma referência
        if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é nulo ou de outra classe
        Relatorio relatorio = (Relatorio) o; // Faz o cast para a classe correta
        return Objects.equals(id, relatorio.id) && Objects.equals(tipoRelatorio, relatorio.tipoRelatorio)
                && Objects.equals(conteudo, relatorio.conteudo) && Objects.equals(dataGeracao, relatorio.dataGeracao);
    }

    // Método hashCode para gerar um código hash baseado nos atributos da classe
    @Override
    public int hashCode() {
        return Objects.hash(id, tipoRelatorio, conteudo, dataGeracao);
    }
}
