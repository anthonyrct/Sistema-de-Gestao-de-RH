package com.anthony.br.Models;

// Importações necessárias para o funcionamento da classe
import java.time.LocalDate; // Importa a classe LocalDate para manipulação de datas
import javax.persistence.*; // Importa as anotações JPA para mapeamento objeto-relacional
import java.util.List; // Importa a classe List para manipulação de coleções

// Anotação para indicar que a classe é uma entidade JPA
@Entity
// Anotação para definir a tabela correspondente no banco de dados
@Table(name = "funcionarios")
public class Funcionario {

    // Atributos da classe Funcionario

    @Id // Indica que este atributo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de ID
    private Long id;

    @Column(nullable = false) // Define a coluna como não nula
    private String nome;

    @Column(nullable = false, unique = true) // Define a coluna como não nula e única
    private String cpf;

    @Column(nullable = false) // Define a coluna como não nula
    private String cargo;

    @Column(nullable = false) // Define a coluna como não nula
    private Double salario;

    @Column(name = "data_contratacao", nullable = false) // Nome da coluna no banco de dados e não nula
    private LocalDate dataContratacao;

    @Column(nullable = false) // Define a coluna como não nula
    private String departamento;

    @Column(name = "data_nascimento", nullable = false) // Nome da coluna no banco de dados e não nula
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true) // Define a coluna como não nula e única
    private String email;

    @Column(nullable = true) // Atributo opcional
    private String endereco;

    @Column(nullable = true) // Atributo opcional
    private String telefone;

    @Column(nullable = true) // Atributo opcional
    private String contaBancaria;

    // Relacionamento ManyToMany com a classe Beneficio
    @ManyToMany(mappedBy = "funcionarios") // Mapeia o relacionamento inverso com a classe Beneficio
    private List<Beneficio> beneficios;

    // Relacionamento OneToMany com a classe PontoEletrico
    @OneToMany(mappedBy = "funcionario") // Mapeia o relacionamento inverso com a classe PontoEletrico
    private List<PontoEletrico> registrosDePonto;

    // Construtor vazio (necessário para JPA)
    public Funcionario() {
    }

    // Construtor completo para inicializar todos os atributos da classe
    public Funcionario(String nome, String cpf, String cargo, Double salario, LocalDate dataContratacao,
            String departamento, LocalDate dataNascimento, String email, String endereco,
            String telefone, String contaBancaria, List<Beneficio> beneficios, List<PontoEletrico> registrosDePonto) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.departamento = departamento;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.contaBancaria = contaBancaria;
        this.beneficios = beneficios;
        this.registrosDePonto = registrosDePonto;
    }

    // Getters e Setters para acessar e modificar os atributos da classe
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }

    public List<PontoEletrico> getRegistrosDePonto() {
        return registrosDePonto;
    }

    public void setRegistrosDePonto(List<PontoEletrico> registrosDePonto) {
        this.registrosDePonto = registrosDePonto;
    }

    // Método para representar a classe como uma string (opcional, mas útil para depuração)
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", departamento='" + departamento + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", contaBancaria='" + contaBancaria + '\'' +
                ", beneficios='" + beneficios + '\'' +
                '}';
    }
}
