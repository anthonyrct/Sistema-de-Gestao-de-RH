package com.anthony.br.Models;

import javax.persistence.*; // Importa as anotações do JPA
import java.util.List; // Importa a lista para armazenar funcionários

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "departamentos") // Define o nome da tabela no banco de dados
public class Departamento {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor da chave primária automaticamente
    private Long id;

    @Column(nullable = false, unique = true) // Define que este campo não pode ser nulo e deve ser único
    private String nome; // Nome do departamento

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Define um relacionamento um-para-muitos com a classe Funcionario
    private List<Funcionario> funcionarios; // Lista de funcionários associados a este departamento

    // Construtor padrão
    public Departamento() {
    }

    // Construtor com parâmetros
    public Departamento(String nome) {
        this.setNome(nome); // Utiliza o método setNome para garantir a validação
    }

    // Getters e Setters
    public Long getId() {
        return id; // Retorna o ID do departamento
    }

    public void setId(Long id) {
        this.id = id; // Define o ID do departamento
    }

    public String getNome() {
        return nome; // Retorna o nome do departamento
    }

    public void setNome(String nome) {
        // Validação para garantir que o nome não seja vazio ou nulo
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do departamento não pode ser vazio.");
        }
        this.nome = nome; // Define o nome do departamento
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios; // Retorna a lista de funcionários associados ao departamento
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios; // Define a lista de funcionários associados ao departamento
    }

    // toString para facilitar a visualização dos dados
    @Override
    public String toString() {
        return "Departamento{id=" + id + ", nome='" + nome + "'}"; // Retorna uma representação em String do objeto Departamento
    }
}
