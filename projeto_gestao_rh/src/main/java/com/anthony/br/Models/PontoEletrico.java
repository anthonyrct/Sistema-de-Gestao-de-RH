package com.anthony.br.Models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Anotação JPA para mapear a classe como uma entidade
@Entity
// Nome da tabela no banco de dados
@Table(name = "pontos_eletronicos")
public class PontoEletrico {

    // Identificador único do registro de ponto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento ManyToOne com a classe Funcionario
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false) // Chave estrangeira
    private Funcionario funcionario;

    // Data e hora em que o registro foi criado
    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    // Hora de entrada do funcionário
    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    // Hora de saída do funcionário, pode ser nula se ainda não registrada
    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    // Duração total das horas trabalhadas
    @Column(name = "horas_trabalhadas")
    private Duration horasTrabalhadas;

    // Duração das horas extras trabalhadas
    @Column(name = "horas_extras")
    private Duration horasExtras;

    // Construtor padrão (necessário para JPA)
    public PontoEletrico() {
    }

    // Construtor que inicializa os atributos obrigatórios
    public PontoEletrico(Funcionario funcionario, LocalDateTime dataRegistro, LocalDateTime horaEntrada) {
        this.funcionario = funcionario;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null; // Inicializa como null
        calcularHoras(); // Chama para calcular horas inicialmente
    }

    // Método para calcular as horas trabalhadas e extras
    public void calcularHoras() {
        // Verifica se as horas de entrada e saída são válidas
        if (horaEntrada != null && horaSaida != null && horaSaida.isAfter(horaEntrada)) {
            this.horasTrabalhadas = Duration.between(horaEntrada, horaSaida);
            this.horasExtras = calcularHorasExtras(horasTrabalhadas); // Calcula horas extras
        } else {
            this.horasTrabalhadas = Duration.ZERO; // Define como zero se não for válido
            this.horasExtras = Duration.ZERO; // Define como zero se não for válido
        }
    }

    // Método privado para calcular as horas extras
    private Duration calcularHorasExtras(Duration horasTrabalhadas) {
        Duration jornadaTrabalho = Duration.ofHours(8); // Jornada padrão de 8 horas
        // Se horas trabalhadas excedem a jornada, calcula horas extras
        if (horasTrabalhadas.compareTo(jornadaTrabalho) > 0) {
            return horasTrabalhadas.minus(jornadaTrabalho);
        }
        return Duration.ZERO; // Retorna zero se não houver horas extras
    }

    // Método estático para filtrar registros de ponto por período
    public static List<PontoEletrico> filtrarPontosPorPeriodo(List<PontoEletrico> registros, LocalDateTime inicio, LocalDateTime fim) {
        return registros.stream()
                .filter(ponto -> !ponto.getDataRegistro().isBefore(inicio) && !ponto.getDataRegistro().isAfter(fim))
                .collect(Collectors.toList()); // Retorna a lista filtrada
    }

    // Getters e Setters para os atributos da classe
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
        calcularHoras(); // Recalcula ao alterar hora de entrada
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        calcularHoras(); // Recalcula ao alterar hora de saída
    }

    public Duration getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public Duration getHorasExtras() {
        return horasExtras;
    }

    // Método para representar o objeto como String
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

    // Método equals para comparar objetos PontoEletrico
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se são o mesmo objeto
        if (!(o instanceof PontoEletrico)) return false; // Verifica se o objeto é do tipo correto
        PontoEletrico that = (PontoEletrico) o; // Realiza o cast
        // Compara os atributos relevantes
        return Objects.equals(id, that.id) &&
                Objects.equals(funcionario, that.funcionario) &&
                Objects.equals(dataRegistro, that.dataRegistro) &&
                Objects.equals(horaEntrada, that.horaEntrada) &&
                Objects.equals(horaSaida, that.horaSaida);
    }

    // Método hashCode para gerar código hash do objeto
    @Override
    public int hashCode() {
        return Objects.hash(id, funcionario, dataRegistro, horaEntrada, horaSaida);
    }
}