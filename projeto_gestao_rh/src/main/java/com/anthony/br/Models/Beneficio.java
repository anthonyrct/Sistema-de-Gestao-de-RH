package com.anthony.br.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beneficios")
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBeneficio tipo;  // Campo para diferenciar o tipo de benefício

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private boolean isPercentual;  // Define se o valor é percentual (true) ou fixo (false)

    @ManyToMany
    @JoinTable(name = "funcionarios_beneficios", 
               joinColumns = @JoinColumn(name = "beneficio_id"), 
               inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<Funcionario> funcionarios;

    // Construtor padrão
    public Beneficio() {
    }

    // Construtor com parâmetros
    public Beneficio(String nome, TipoBeneficio tipo, double valor, boolean isPercentual) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.isPercentual = isPercentual;
    }

    public enum TipoBeneficio {
        VALE_TRANSPORTE,
        VALE_REFEICAO,
        ADICIONAL,
        DESCONTO,
        OUTRO
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBeneficio getTipo() {
        return tipo;
    }

    public void setTipo(TipoBeneficio tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPercentual() {
        return isPercentual;
    }

    public void setPercentual(boolean isPercentual) {
        this.isPercentual = isPercentual;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
