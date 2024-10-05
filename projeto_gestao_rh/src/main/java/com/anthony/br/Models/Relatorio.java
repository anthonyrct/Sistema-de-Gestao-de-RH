package com.anthony.br.Models;

import javax.persistence.*;
import java.util.Date;
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
    private Date dataGeracao;

    // Construtores
    public Relatorio() {
    }

    public Relatorio(Long id, String tipoRelatorio, String conteudo, Date dataGeracao) {
        this.id = id;
        this.tipoRelatorio = tipoRelatorio;
        this.conteudo = conteudo;
        this.dataGeracao = dataGeracao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
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
