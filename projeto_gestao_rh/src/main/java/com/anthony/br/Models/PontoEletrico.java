package com.anthony.br.Models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "pontos_eletronicos")
public class PontoEletrico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

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
    public PontoEletrico() {
    }

    public PontoEletrico(Funcionario funcionario, LocalDateTime dataRegistro, LocalDateTime horaEntrada) {
        this.funcionario = funcionario;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null; // Inicializa como null
        calcularHoras(); // Pode ser ajustado para evitar cálculos desnecessários
    }

    // Método para calcular horas trabalhadas e extras
    public void calcularHoras() {
        if (horaEntrada != null && horaSaida != null && horaSaida.isAfter(horaEntrada)) {
            this.horasTrabalhadas = Duration.between(horaEntrada, horaSaida);
            this.horasExtras = calcularHorasExtras(horasTrabalhadas);
        } else {
            this.horasTrabalhadas = Duration.ZERO; // Define como zero se não for válido
            this.horasExtras = Duration.ZERO; // Define como zero se não for válido
        }
    }

    private Duration calcularHorasExtras(Duration horasTrabalhadas) {
        Duration jornadaTrabalho = Duration.ofHours(8);
        if (horasTrabalhadas.compareTo(jornadaTrabalho) > 0) {
            return horasTrabalhadas.minus(jornadaTrabalho);
        }
        return Duration.ZERO;
    }

    public static List<PontoEletrico> filtrarPontosPorPeriodo(List<PontoEletrico> registros, LocalDateTime inicio, LocalDateTime fim) {
        return registros.stream()
                .filter(ponto -> !ponto.getDataRegistro().isBefore(inicio) && !ponto.getDataRegistro().isAfter(fim))
                .collect(Collectors.toList());
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
        calcularHoras(); // Recalcular sempre que a entrada é alterada
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        calcularHoras(); // Recalcular sempre que a saída é alterada
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
                ", horasTrabalhadas=" + horasTrabalhadas.toHours() + "h " + (horasTrabalhadas.toMinutes() % 60) + "m" +
                ", horasExtras=" + horasExtras.toHours() + "h " + (horasExtras.toMinutes() % 60) + "m" +
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