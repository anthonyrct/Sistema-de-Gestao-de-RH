package com.anthony.br.Models;

import javax.persistence.*; // Importa as anotações do JPA
import java.util.Objects; // Importa a classe Objects para facilitar comparações

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "candidatos") // Define o nome da tabela no banco de dados
public class Candidato {
    
    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor da chave primária automaticamente
    private int id;

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private String nome;

    @Column(nullable = false, unique = true) // Define que este campo não pode ser nulo e deve ser único
    private String email;

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private String telefone;

    @Column(nullable = false) // Define que este campo não pode ser nulo
    private String curriculo;

    @ManyToOne // Define um relacionamento muitos-para-um com a classe Recrutamento
    @JoinColumn(name = "recrutamento_id") // Define a coluna que referencia a entidade Recrutamento
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
        // Validação para garantir que o nome não seja vazio
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Validação para garantir que o email seja válido
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        // Validação para garantir que o telefone tenha pelo menos 10 dígitos
        if (telefone == null || telefone.length() < 10) {
            throw new IllegalArgumentException("Telefone inválido. Deve ter pelo menos 10 dígitos.");
        }
        this.telefone = telefone;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo; // Pode ser qualquer valor
    }

    public Recrutamento getRecrutamento() {
        return recrutamento;
    }

    public void setRecrutamento(Recrutamento recrutamento) {
        this.recrutamento = recrutamento; // Pode ser associado a um recrutamento
    }

    // Método toString para representação em String do objeto Candidato
    @Override
    public String toString() {
        return "Candidato{id=" + id + ", nome='" + nome + "', email='" + email + "', telefone='" + telefone +
                "', curriculo='" + curriculo + "'}";
    }

    // Método equals para comparação de igualdade entre objetos Candidato
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Verifica se são o mesmo objeto
        if (o == null || getClass() != o.getClass())
            return false; // Verifica se o objeto é nulo ou não é da mesma classe
        Candidato that = (Candidato) o; // Faz o cast para Candidato
        // Compara os campos relevantes para igualdade
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) &&
                Objects.equals(telefone, that.telefone) && Objects.equals(curriculo, that.curriculo);
    }

    // Método hashCode para gerar um código hash para o objeto
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, curriculo); // Gera o código hash baseado nos campos relevantes
    }
}
