package main.java.com.anthony.br.Models;

import java.util.Date;
import java.util.Objects;

public class PontoEletrico {
    private int id;
    private int funcionarioId; // ID do funcionário que registrou o ponto
    private Date dataRegistro;
    private Date horaEntrada;
    private Date horaSaida;

    // Construtores
    public PontoEletronico() {}

    public PontoEletronico(int id, int funcionarioId, Date dataRegistro, Date horaEntrada, Date horaSaida) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
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
        return "PontoEletronico{" +
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
        PontoEletronico that = (PontoEletronico) o;
        return id == that.id &&
                funcionarioId == that.funcionarioId &&
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
