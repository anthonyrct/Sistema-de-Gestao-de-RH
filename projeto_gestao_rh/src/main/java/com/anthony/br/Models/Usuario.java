package com.anthony.br.Models;

// Importando as anotações necessárias para a JPA e validação
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

// Definindo a entidade JPA para a tabela "usuarios"
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Identificador único da entidade, gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário, não pode ser nulo e não pode ser vazio
    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    // Email do usuário, não pode ser nulo, deve ser único e ter formato válido
    @Column(nullable = false, unique = true)
    @Email(message = "Email deve ser válido.")
    @NotBlank(message = "O email não pode ser vazio.")
    private String email;

    // Senha do usuário, não pode ser nula, deve ter pelo menos 6 caracteres e não pode ser vazia
    @Column(nullable = false)
    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    // Usando Enum para definir o papel do usuário
    @Enumerated(EnumType.STRING) // Armazena o nome do enum no banco de dados
    @Column(nullable = false)
    private Role role; // Tipo enum para papéis de usuário

    // Construtores
    public Usuario() {
    }

    // Construtor que recebe todos os parâmetros necessários
    public Usuario(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    // Não é necessário um setter para id, pois ele é gerado pelo banco

    public String getNome() {
        return nome;
    }

    // Setter para o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    // Setter para o email do usuário
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    // Setter para a senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    // Setter para o papel do usuário
    public void setRole(Role role) {
        this.role = role;
    }

    // Método toString para representação textual da classe
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', email='" + email + "', role='" + role + "'}";
    }

    // Método equals para comparar duas instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é a mesma referência
        if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é nulo ou de outra classe
        Usuario usuario = (Usuario) o; // Faz o cast para a classe correta
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) &&
               Objects.equals(email, usuario.email) && role == usuario.role;
    }

    // Método hashCode para gerar um código hash baseado nos atributos da classe
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, role);
    }

    // Enum para definir os papéis de usuário
    public enum Role {
        FUNCIONARIO, // Papel de funcionário
        RH // Papel de Recursos Humanos
    }
}
