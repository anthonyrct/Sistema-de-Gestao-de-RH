package com.anthony.br.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recrutamentos")
public class Recrutamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vaga;

    @Column(name = "data_abertura", nullable = false)
    private Date dataAbertura;

    @Column(name = "data_fechamento")
    private Date dataFechamento;

    @OneToMany(mappedBy = "recrutamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidato> candidatos;

    // Construtores
    public Recrutamento() {
        this.candidatos = new ArrayList<>();
    }

    public Recrutamento(Long id, String vaga, Date dataAbertura, Date dataFechamento, List<Candidato> candidatos) {
        this.id = id;
        this.vaga = vaga;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.candidatos = candidatos != null ? candidatos : new ArrayList<>();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos != null ? candidatos : new ArrayList<>();
    }

    // toString
    @Override
    public String toString() {
        return "Recrutamento{id=" + id + ", vaga='" + vaga + "', dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento + ", candidatos=" + candidatos + "}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Recrutamento that = (Recrutamento) o;
        return Objects.equals(id, that.id) && Objects.equals(vaga, that.vaga) &&
                Objects.equals(dataAbertura, that.dataAbertura) &&
                Objects.equals(dataFechamento, that.dataFechamento) &&
                Objects.equals(candidatos, that.candidatos);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, vaga, dataAbertura, dataFechamento, candidatos);
    }
}
