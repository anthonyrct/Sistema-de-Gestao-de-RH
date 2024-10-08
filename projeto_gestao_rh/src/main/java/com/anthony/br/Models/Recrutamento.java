package com.anthony.br.Models;

// Importando as anotações necessárias para a JPA
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Definindo a entidade JPA para a tabela "recrutamentos"
@Entity
@Table(name = "recrutamentos")
public class Recrutamento {

    // Identificador único da entidade, gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da vaga, não pode ser nulo
    @Column(nullable = false)
    private String vaga;

    // Data de abertura da vaga, não pode ser nulo
    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    // Data de fechamento da vaga, pode ser nulo
    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    // Lista de candidatos associados a este recrutamento
    @OneToMany(mappedBy = "recrutamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidato> candidatos;

    // Construtor padrão, inicializando a lista de candidatos
    public Recrutamento() {
        this.candidatos = new ArrayList<>();
    }

    // Construtor que recebe todos os parâmetros
    public Recrutamento(Long id, String vaga, LocalDate dataAbertura, LocalDate dataFechamento, List<Candidato> candidatos) {
        this.id = id;
        this.vaga = vaga;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        // Inicializa a lista de candidatos ou cria uma nova lista se for nula
        this.candidatos = candidatos != null ? candidatos : new ArrayList<>();
    }

    // Método para adicionar um candidato à lista e configurar a referência de volta
    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
        candidato.setRecrutamento(this); // Definindo a referência de volta para o recrutamento
    }

    // Método para remover um candidato da lista e limpar a referência de volta
    public void removerCandidato(Candidato candidato) {
        candidatos.remove(candidato);
        candidato.setRecrutamento(null); // Removendo a referência de volta
    }

    // Getters e Setters para os atributos da classe
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

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos != null ? candidatos : new ArrayList<>();
    }

    // Método toString para representação textual da classe
    @Override
    public String toString() {
        return "Recrutamento{id=" + id + ", vaga='" + vaga + "', dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento + ", candidatos=" + candidatos + "}";
    }

    // Método equals para comparar duas instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma referência
        if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é nulo ou de outra classe
        Recrutamento that = (Recrutamento) o; // Faz o cast para a classe correta
        return Objects.equals(id, that.id) && Objects.equals(vaga, that.vaga) &&
                Objects.equals(dataAbertura, that.dataAbertura) &&
                Objects.equals(dataFechamento, that.dataFechamento) &&
                Objects.equals(candidatos, that.candidatos);
    }

    // Método hashCode para gerar um código hash baseado nos atributos da classe
    @Override
    public int hashCode() {
        return Objects.hash(id, vaga, dataAbertura, dataFechamento, candidatos);
    }
}
