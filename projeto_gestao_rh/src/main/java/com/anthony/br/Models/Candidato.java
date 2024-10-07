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

    @ManyToOne
    @JoinColumn(name = "recrutamento_id")
    private Recrutamento recrutamento; // Nova referência para Recrutamento

    // Construtores
    public Candidato() {
    }

    public Candidato(String nome, String email, String telefone, String curriculo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.curriculo = curriculo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    // Não expor setter para id, já que é gerado pelo banco
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.length() < 10) {
            throw new IllegalArgumentException("Telefone inválido. Deve ter pelo menos 10 dígitos.");
        }
        this.telefone = telefone;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public Recrutamento getRecrutamento() {
        return recrutamento;
    }

    public void setRecrutamento(Recrutamento recrutamento) {
        this.recrutamento = recrutamento;
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
