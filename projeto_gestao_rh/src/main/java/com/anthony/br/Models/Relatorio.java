package com.anthony.br.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_relatorio", nullable = false)
    private String tipoRelatorio; // Ex: desempenho, horas trabalhadas, etc.

    @Column(nullable = false)
    private String conteudo;

    @Column(name = "data_geracao", nullable = false)
    private LocalDateTime dataGeracao; // Usando LocalDateTime ao invés de Date

    // Construtores
    public Relatorio() {
    }

    public Relatorio(String tipoRelatorio, String conteudo, LocalDateTime dataGeracao) {
        this.tipoRelatorio = tipoRelatorio;
        this.conteudo = conteudo;
        this.dataGeracao = dataGeracao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    // Não é necessário um setter para id, pois é gerado pelo banco
    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        if (tipoRelatorio == null || tipoRelatorio.isEmpty()) {
            throw new IllegalArgumentException("O tipo de relatório não pode ser vazio.");
        }
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getConteudo() {
        return conteudo;
    }

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

    // toString
    @Override
    public String toString() {
        return "Relatorio{id=" + id + ", tipoRelatorio='" + tipoRelatorio + "', dataGeracao=" + dataGeracao + "}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Relatorio relatorio = (Relatorio) o;
        return Objects.equals(id, relatorio.id) && Objects.equals(tipoRelatorio, relatorio.tipoRelatorio)
                && Objects.equals(conteudo, relatorio.conteudo) && Objects.equals(dataGeracao, relatorio.dataGeracao);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, tipoRelatorio, conteudo, dataGeracao);
    }
}
