package com.anthony.br.Models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pontos_eletronicos") // Correção do nome da tabela
public class PontoEletrico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario; // Relacionamento ManyToOne com Funcionario

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @Column(name = "horas_trabalhadas")
    private Duration horasTrabalhadas;

    @Column(name = "horas_extras")
    private Duration horasExtras;

    // Construtores
    public PontoEletrico() {}

    public PontoEletrico(Funcionario funcionario, LocalDateTime dataRegistro, LocalDateTime horaEntrada) {
        this.funcionario = funcionario;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        calcularHoras(); // Calcula logo ao criar o ponto
    }

    // Método para calcular horas trabalhadas e extras
    public void calcularHoras() { // Mude para public
        if (horaEntrada != null && horaSaida != null && horaSaida.isAfter(horaEntrada)) {
            this.horasTrabalhadas = Duration.between(horaEntrada, horaSaida);
            this.horasExtras = calcularHorasExtras(horasTrabalhadas);
        } else {
            throw new IllegalArgumentException("Horário de saída deve ser posterior ao de entrada.");
        }
    }

    private Duration calcularHorasExtras(Duration horasTrabalhadas) {
        Duration jornadaTrabalho = Duration.ofHours(8); // Jornada padrão de 8 horas
        if (horasTrabalhadas.compareTo(jornadaTrabalho) > 0) {
            return horasTrabalhadas.minus(jornadaTrabalho);
        }
        return Duration.ZERO;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
        calcularHoras();
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        calcularHoras(); // Calcular horas sempre que a hora de saída é definida
    }

    public Duration getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public Duration getHorasExtras() {
        return horasExtras;
    }

    @Override
    public String toString() {
        return "PontoEletrico{" +
                "id=" + id +
                ", funcionario=" + funcionario +
                ", dataRegistro=" + dataRegistro +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                ", horasTrabalhadas=" + horasTrabalhadas +
                ", horasExtras=" + horasExtras +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoEletrico)) return false;
        PontoEletrico that = (PontoEletrico) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(funcionario, that.funcionario) &&
                Objects.equals(dataRegistro, that.dataRegistro) &&
                Objects.equals(horaEntrada, that.horaEntrada) &&
                Objects.equals(horaSaida, that.horaSaida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, funcionario, dataRegistro, horaEntrada, horaSaida);
    }
}