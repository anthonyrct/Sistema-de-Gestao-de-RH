package com.anthony.br.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidatos")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String curriculo;

    // Construtores
    public Candidato() {
    }

    public Candidato(int id, String nome, String email, String telefone, String curriculo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.curriculo = curriculo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    // toString
    @Override
    public String toString() {
        return "Candidato{id=" + id + ", nome='" + nome + "', email='" + email + "', telefone='" + telefone +
                "', curriculo='" + curriculo + "'}";
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Candidato that = (Candidato) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) &&
                Objects.equals(telefone, that.telefone) && Objects.equals(curriculo, that.curriculo);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, curriculo);
    }
}
