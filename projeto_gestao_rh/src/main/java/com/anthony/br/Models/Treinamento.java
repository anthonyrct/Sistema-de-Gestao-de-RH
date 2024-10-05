package com.anthony.br.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "treinamentos")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private Date dataInicio;

    @Column(name = "data_fim", nullable = false)
    private Date dataFim;

    @Column(nullable = false)
    private int cargaHoraria;

    // Construtores
    public Treinamento() {
    }

    public Treinamento(Long id, String titulo, String descricao, Date dataInicio, Date dataFim, int cargaHoraria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // toString
    @Override
    public String toString() {
        return "Treinamento{id=" + id + ", titulo='" + titulo + "', descricao='" + descricao + "', dataInicio=" + dataInicio +
               ", dataFim=" + dataFim + ", cargaHoraria=" + cargaHoraria + "}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Treinamento that = (Treinamento) o;
        return cargaHoraria == that.cargaHoraria && Objects.equals(id, that.id) && Objects.equals(titulo, that.titulo) &&
               Objects.equals(descricao, that.descricao) && Objects.equals(dataInicio, that.dataInicio) &&
               Objects.equals(dataFim, that.dataFim);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataInicio, dataFim, cargaHoraria);
    }
}
