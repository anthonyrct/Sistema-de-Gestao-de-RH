package com.anthony.br.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @Column(nullable = false, unique = true)
    @Email(message = "Email deve ser válido.")
    @NotBlank(message = "O email não pode ser vazio.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    @Enumerated(EnumType.STRING) // Usando Enum para o papel
    @Column(nullable = false)
    private Role role; // Tipo enum para papéis de usuário

    // Construtores
    public Usuario() {
    }

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

    // Não é necessário um setter para id, pois é gerado pelo banco

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // toString
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', email='" + email + "', role='" + role + "'}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) &&
               Objects.equals(email, usuario.email) && role == usuario.role;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, role);
    }

    // Enum para papéis de usuário
    public enum Role {
        FUNCIONARIO,
        RH
    }
}
