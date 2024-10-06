package com.anthony.br.Models;

import java.time.LocalDate;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Double salario;

    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;

    @Column(nullable = false)
    private String departamento;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true) // Atributo opcional
    private String endereco;

    @Column(nullable = true) // Atributo opcional
    private String telefone;

    @Column(nullable = true) // Atributo opcional
    private String contaBancaria;

    @ManyToMany(mappedBy = "funcionarios") // Mapeia o relacionamento com benefícios
    private List<Beneficio> beneficios;

    // Construtor vazio (necessário para JPA)
    public Funcionario() {
    }

    // Construtor completo
    public Funcionario(String nome, String cpf, String cargo, Double salario, LocalDate dataContratacao,
            String departamento, LocalDate dataNascimento, String email, String endereco,
            String telefone, String contaBancaria, List<Beneficio> beneficios) {
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

    // Métodos adicionais, caso necessário
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
