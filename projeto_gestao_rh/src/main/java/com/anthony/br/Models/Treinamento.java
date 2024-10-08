package com.anthony.br.Models;

// Importando as anotações necessárias para a JPA
import javax.persistence.*;
import java.time.LocalDate; // Usando LocalDate para representar datas
import java.util.Objects;

// Definindo a entidade JPA para a tabela "treinamentos"
@Entity
@Table(name = "treinamentos")
public class Treinamento {

    // Identificador único da entidade, gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título do treinamento, não pode ser nulo
    @Column(nullable = false)
    private String titulo;

    // Descrição do treinamento, não pode ser nula
    @Column(nullable = false)
    private String descricao;

    // Data de início do treinamento, não pode ser nula. Usando LocalDate ao invés de Date
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio; 

    // Data de fim do treinamento, não pode ser nula. Usando LocalDate ao invés de Date
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim; 

    // Carga horária do treinamento, deve ser um número positivo
    @Column(nullable = false)
    private int cargaHoraria;

    // Construtores
    public Treinamento() {
    }

    // Construtor que recebe todos os parâmetros necessários
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

    // Não é necessário um setter para id, pois ele é gerado pelo banco

    public String getTitulo() {
        return titulo;
    }

    // Setter para o título do treinamento com validação
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título do treinamento não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Setter para a descrição do treinamento com validação
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do treinamento não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    // Setter para a data de início do treinamento com validação
    public void setDataInicio(LocalDate dataInicio) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    // Setter para a data de fim do treinamento com validação
    public void setDataFim(LocalDate dataFim) {
        if (dataFim == null) {
            throw new IllegalArgumentException("A data de fim não pode ser nula.");
        }
        // Verifica se a data de fim é anterior à data de início
        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
        this.dataFim = dataFim;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // Setter para a carga horária do treinamento com validação
    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    // Método para calcular a duração do treinamento em dias
    public long calcularDuracao() {
        return dataInicio.until(dataFim).getDays();
    }

    // Método toString para representação textual da classe
    @Override
    public String toString() {
        return "Treinamento{id=" + id + ", titulo='" + titulo + "', descricao='" + descricao +
               "', dataInicio=" + dataInicio + ", dataFim=" + dataFim +
               ", cargaHoraria=" + cargaHoraria + "}";
    }

    // Método equals para comparar duas instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma referência
        if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é nulo ou de outra classe
        Treinamento that = (Treinamento) o; // Faz o cast para a classe correta
        return cargaHoraria == that.cargaHoraria && Objects.equals(id, that.id) &&
               Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao) &&
               Objects.equals(dataInicio, that.dataInicio) && Objects.equals(dataFim, that.dataFim);
    }

    // Método hashCode para gerar um código hash baseado nos atributos da classe
    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataInicio, dataFim, cargaHoraria);
    }
}
