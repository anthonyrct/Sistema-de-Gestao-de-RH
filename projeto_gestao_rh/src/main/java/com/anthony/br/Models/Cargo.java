package com.anthony.br.Models;

import javax.persistence.*; // Importa as anotações do JPA
import java.util.List; // Importa a lista para armazenar funcionários

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "cargos") // Define o nome da tabela no banco de dados
public class Cargo {
    
    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor da chave primária automaticamente
    private Long id;

    @Column(nullable = false, unique = true) // Define que este campo não pode ser nulo e deve ser único
    private String nome;

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private Double salarioBase; // Armazena o salário base associado ao cargo

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Define um relacionamento um-para-muitos com a classe Funcionario
    private List<Funcionario> funcionarios; // Lista de funcionários associados a este cargo

    // Construtor padrão
    public Cargo() {
    }

    // Construtor com parâmetros
    public Cargo(String nome, Double salarioBase) {
        this.setNome(nome); // Utiliza o método setNome para garantir a validação
        this.setSalarioBase(salarioBase); // Utiliza o método setSalarioBase para garantir a validação
    }

    // Getters e Setters
    public Long getId() {
        return id; // Retorna o ID do cargo
    }

    public void setId(Long id) {
        this.id = id; // Define o ID do cargo
    }

    public String getNome() {
        return nome; // Retorna o nome do cargo
    }

    public void setNome(String nome) {
        // Validação para garantir que o nome não seja vazio ou nulo
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cargo não pode ser vazio.");
        }
        this.nome = nome; // Define o nome do cargo
    }

    public Double getSalarioBase() {
        return salarioBase; // Retorna o salário base do cargo
    }

    public void setSalarioBase(Double salarioBase) {
        // Validação para garantir que o salário base não seja nulo e seja maior ou igual a zero
        if (salarioBase == null || salarioBase < 0) {
            throw new IllegalArgumentException("Salário base deve ser maior ou igual a zero.");
        }
        this.salarioBase = salarioBase; // Define o salário base do cargo
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios; // Retorna a lista de funcionários associados ao cargo
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios; // Define a lista de funcionários associados ao cargo
    }
}
