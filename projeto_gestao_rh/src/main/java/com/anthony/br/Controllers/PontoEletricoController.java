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

    private EntityManagerFactory emf;

    public PontoEletricoController() {
        this.emf = Persistence.createEntityManagerFactory("gestao_rh_persistence_unit");
    }

    public void registrarPonto(PontoEletrico pontoEletrico) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pontoEletrico);
        em.getTransaction().commit();
        em.close();
    }

    public List<PontoEletrico> buscarPontosPorFuncionario(Long funcionarioId) {
        EntityManager em = emf.createEntityManager();
        List<PontoEletrico> pontos = em
                .createQuery("SELECT p FROM PontoEletrico p WHERE p.funcionarioId = :funcionarioId",
                        PontoEletrico.class)
                .setParameter("funcionarioId", funcionarioId)
                .getResultList();
        em.close();
        return pontos;
    }

    public PontoEletrico atualizarPonto(Long id, PontoEletrico pontoAtualizado) {
        EntityManager em = emf.createEntityManager();
        PontoEletrico ponto = em.find(PontoEletrico.class, id);

        if (ponto != null) {
            em.getTransaction().begin();
            ponto.setHoraEntrada(pontoAtualizado.getHoraEntrada());
            ponto.setHoraSaida(pontoAtualizado.getHoraSaida());
            ponto.calcularHoras(); // Recalcular horas trabalhadas e extras
            em.getTransaction().commit();
        }

        em.close();
        return ponto;
    }

    public void deletarPonto(Long id) {
        EntityManager em = emf.createEntityManager();
        PontoEletrico ponto = em.find(PontoEletrico.class, id);

        if (ponto != null) {
            em.getTransaction().begin();
            em.remove(ponto);
            em.getTransaction().commit();
        }

        em.close();
    }
}
