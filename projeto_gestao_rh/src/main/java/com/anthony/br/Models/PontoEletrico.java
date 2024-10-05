package com.anthony.br.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pontos_eletricos")
public class PontoEletrico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId; // ID do funcionário que registrou o ponto

    @Column(name = "data_registro", nullable = false)
    private Date dataRegistro;

    @Column(name = "hora_entrada", nullable = false)
    private Date horaEntrada;

    @Column(name = "hora_saida", nullable = false)
    private Date horaSaida;

    // Construtores
    public PontoEletrico() {}

    public PontoEletrico(Long id, Long funcionarioId, Date dataRegistro, Date horaEntrada, Date horaSaida) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    // toString
    @Override
    public String toString() {
        return "PontoEletrico{" +
                "id=" + id +
                ", funcionarioId=" + funcionarioId +
                ", dataRegistro=" + dataRegistro +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PontoEletrico that = (PontoEletrico) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(funcionarioId, that.funcionarioId) &&
                Objects.equals(dataRegistro, that.dataRegistro) &&
                Objects.equals(horaEntrada, that.horaEntrada) &&
                Objects.equals(horaSaida, that.horaSaida);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, funcionarioId, dataRegistro, horaEntrada, horaSaida);
    }
}
