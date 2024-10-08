package com.anthony.br.Models;

import javax.persistence.*; // Importa as anotações do JPA
import java.util.List;

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "beneficios") // Define o nome da tabela no banco de dados
public class Beneficio {
    
    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor da chave primária automaticamente
    private Long id;

    @Column(nullable = false, unique = true) // Define que este campo não pode ser nulo e deve ser único
    private String nome;

    @Enumerated(EnumType.STRING) // Armazena o tipo de benefício como uma string
    @Column(nullable = false) // Define que este campo não pode ser nulo
    private TipoBeneficio tipo;  // Campo para diferenciar o tipo de benefício

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private double valor; // Valor do benefício

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private boolean isPercentual;  // Define se o valor é percentual (true) ou fixo (false)

    @ManyToMany // Define um relacionamento muitos-para-muitos com a classe Funcionario
    @JoinTable(name = "funcionarios_beneficios",  // Nome da tabela de junção
               joinColumns = @JoinColumn(name = "beneficio_id"), // Coluna que referencia esta entidade
               inverseJoinColumns = @JoinColumn(name = "funcionario_id")) // Coluna que referencia a entidade Funcionario
    private List<Funcionario> funcionarios; // Lista de funcionários associados a este benefício

    // Construtor padrão
    public Beneficio() {
    }

    // Construtor com parâmetros para inicializar os atributos
    public Beneficio(String nome, TipoBeneficio tipo, double valor, boolean isPercentual) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.isPercentual = isPercentual;
    }

    // Enumeração para os tipos de benefício
    public enum TipoBeneficio {
        VALE_TRANSPORTE,
        VALE_REFEICAO,
        ADICIONAL,
        DESCONTO,
        OUTRO
    }

    // Getters e Setters para acessar e modificar os atributos
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
