package com.anthony.br.Controllers;

import com.anthony.br.Models.Funcionario;
import com.anthony.br.Models.PontoEletrico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PontoEletricoController {

    private EntityManagerFactory emf; // Fábrica de EntityManager para gerenciar a conexão com o banco de dados

    // Construtor que inicializa a fábrica de EntityManager com a unidade de persistência
    public PontoEletricoController() {
        this.emf = Persistence.createEntityManagerFactory("gestao_rh_persistence_unit");
    }

    // Método para registrar um ponto eletrônico no banco de dados
    public void registrarPonto(PontoEletrico pontoEletrico) {
        EntityManager em = emf.createEntityManager(); // Cria um novo EntityManager
        em.getTransaction().begin(); // Inicia a transação
        em.persist(pontoEletrico); // Persiste o objeto PontoEletrico no banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close(); // Fecha o EntityManager
    }

    // Método para buscar todos os pontos registrados de um funcionário específico
    public List<PontoEletrico> buscarPontosPorFuncionario(Long funcionarioId) {
        EntityManager em = emf.createEntityManager(); // Cria um novo EntityManager
        List<PontoEletrico> pontos = em
                .createQuery("SELECT p FROM PontoEletrico p WHERE p.funcionario.id = :funcionarioId", PontoEletrico.class)
                .setParameter("funcionarioId", funcionarioId) // Define o parâmetro da consulta
                .getResultList(); // Executa a consulta e obtém a lista de pontos
        em.close(); // Fecha o EntityManager
        return pontos; // Retorna a lista de pontos encontrados
    }

    // Método para atualizar um ponto eletrônico existente
    public PontoEletrico atualizarPonto(Long id, PontoEletrico pontoAtualizado) {
        EntityManager em = emf.createEntityManager(); // Cria um novo EntityManager
        PontoEletrico ponto = em.find(PontoEletrico.class, id); // Busca o ponto eletrônico pelo ID

        if (ponto != null) { // Verifica se o ponto foi encontrado
            em.getTransaction().begin(); // Inicia a transação
            // Atualiza os atributos do ponto com os valores do ponto atualizado
            ponto.setHoraEntrada(pontoAtualizado.getHoraEntrada());
            ponto.setHoraSaida(pontoAtualizado.getHoraSaida());
            ponto.calcularHoras(); // Recalcula horas trabalhadas e extras
            em.getTransaction().commit(); // Confirma a transação
        }

        em.close(); // Fecha o EntityManager
        return ponto; // Retorna o ponto atualizado
    }

    // Método para deletar um ponto eletrônico pelo ID
    public void deletarPonto(Long id) {
        EntityManager em = emf.createEntityManager(); // Cria um novo EntityManager
        PontoEletrico ponto = em.find(PontoEletrico.class, id); // Busca o ponto eletrônico pelo ID

        if (ponto != null) { // Verifica se o ponto foi encontrado
            em.getTransaction().begin(); // Inicia a transação
            em.remove(ponto); // Remove o ponto do banco de dados
            em.getTransaction().commit(); // Confirma a transação
        }

        em.close(); // Fecha o EntityManager
    }
}
