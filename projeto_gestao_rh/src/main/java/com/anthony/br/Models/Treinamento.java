package com.anthony.br.Models;

import javax.persistence.*;
import java.time.LocalDate; // Usando LocalDate
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
    private LocalDate dataInicio; // Usando LocalDate ao invés de Date

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim; // Usando LocalDate ao invés de Date

    @Column(nullable = false)
    private int cargaHoraria;

    // Construtores
    public Treinamento() {
    }

    public Treinamento(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFim, int cargaHoraria) {
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

    // Não é necessário um setter para id, pois é gerado pelo banco

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título do treinamento não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do treinamento não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        if (dataFim == null) {
            throw new IllegalArgumentException("A data de fim não pode ser nula.");
        }
        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
        this.dataFim = dataFim;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    // Método para calcular a duração do treinamento
    public long calcularDuracao() {
        return dataInicio.until(dataFim).getDays();
    }

    // toString
    @Override
    public String toString() {
        return "Treinamento{id=" + id + ", titulo='" + titulo + "', descricao='" + descricao +
               "', dataInicio=" + dataInicio + ", dataFim=" + dataFim +
               ", cargaHoraria=" + cargaHoraria + "}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treinamento that = (Treinamento) o;
        return cargaHoraria == that.cargaHoraria && Objects.equals(id, that.id) &&
               Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao) &&
               Objects.equals(dataInicio, that.dataInicio) && Objects.equals(dataFim, that.dataFim);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataInicio, dataFim, cargaHoraria);
    }
}
