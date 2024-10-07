package com.anthony.br.Models;

import java.time.LocalDate;
import java.util.List;

import com.anthony.br.Exceptions.InvalidFolhaPagamentoException;

public class FolhaPagamento {
    private Funcionario funcionario;
    private LocalDate mesReferencia;
    private double salarioBruto;
    private double inss;
    private double fgts;
    private double totalBeneficios;
    private double salarioLiquido;
    private List<PontoEletrico> registrosPonto; // Campo para registros de ponto

    private static final double CARGA_HORARIA_PADRAO = 160.0; // Carga horária mensal padrão
    private static final double HORAS_EXTRA_MULTIPLICADOR = 1.5; // Multiplicador de horas extras

    // Construtor
    public FolhaPagamento(Funcionario funcionario, LocalDate mesReferencia, List<PontoEletrico> registrosPonto)
            throws InvalidFolhaPagamentoException {
        if (funcionario == null) {
            throw new InvalidFolhaPagamentoException("Funcionário não pode ser nulo.");
        }

        if (mesReferencia == null) {
            throw new InvalidFolhaPagamentoException("A data de referência não pode ser nula.");
        }

        if (registrosPonto == null || registrosPonto.isEmpty()) {
            throw new InvalidFolhaPagamentoException("Registros de ponto não podem ser nulos ou vazios.");
        }

        this.funcionario = funcionario;
        this.mesReferencia = mesReferencia;
        this.registrosPonto = registrosPonto; // Atribui os registros de ponto
        validarFuncionario(funcionario); // Validações iniciais
        calcularValores();
    }

    // Método para calcular os valores da folha de pagamento
    private void calcularValores() throws InvalidFolhaPagamentoException {
        double horasTrabalhadas = calcularHorasTrabalhadas(registrosPonto);
        double horasExtras = calcularHorasExtras(registrosPonto);

        // Ajusta o salário bruto com base nas horas trabalhadas
        this.salarioBruto = (horasTrabalhadas / CARGA_HORARIA_PADRAO) * funcionario.getSalario();

        if (salarioBruto <= 0) {
            throw new InvalidFolhaPagamentoException("O salário bruto deve ser maior que zero.");
        }

        // Calcula INSS e FGTS
        this.inss = calcularInss(salarioBruto);
        this.fgts = salarioBruto * 0.08; // 8% de FGTS

        // Calcula o total de benefícios
        this.totalBeneficios = calcularTotalBeneficios(funcionario.getBeneficios());

        // Calcula o salário líquido (com base no salário bruto, INSS e benefícios)
        this.salarioLiquido = salarioBruto - inss + totalBeneficios
                + (horasExtras * (funcionario.getSalario() / CARGA_HORARIA_PADRAO) * HORAS_EXTRA_MULTIPLICADOR);

        if (salarioLiquido < 0) {
            throw new InvalidFolhaPagamentoException("O salário líquido não pode ser negativo.");
        }
    }

    // Validação dos dados do funcionário
    private void validarFuncionario(Funcionario funcionario) throws InvalidFolhaPagamentoException {
        if (funcionario.getSalario() <= 0) {
            throw new InvalidFolhaPagamentoException("O salário do funcionário deve ser maior que zero.");
        }

        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            throw new InvalidFolhaPagamentoException("O CPF do funcionário é inválido.");
        }

        if (funcionario.getDataNascimento() == null || funcionario.getDataNascimento().isAfter(LocalDate.now())) {
            throw new InvalidFolhaPagamentoException("A data de nascimento do funcionário é inválida.");
        }

        if (funcionario.getDataContratacao() == null || funcionario.getDataContratacao().isAfter(LocalDate.now())) {
            throw new InvalidFolhaPagamentoException("A data de contratação do funcionário é inválida.");
        }

        for (Beneficio beneficio : funcionario.getBeneficios()) {
            if (beneficio.getValor() < 0) {
                throw new InvalidFolhaPagamentoException(
                        "O valor do benefício " + beneficio.getNome() + " não pode ser negativo.");
            }
        }
    }

    // Método para calcular INSS
    private double calcularInss(double salarioBruto) {
        double inss = 0.0;
        if (salarioBruto <= 1302.00) {
            inss = salarioBruto * 0.075;
        } else if (salarioBruto <= 2571.29) {
            inss = 1302.00 * 0.075 + (salarioBruto - 1302.00) * 0.09;
        } else if (salarioBruto <= 3856.94) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (salarioBruto - 2571.29) * 0.12;
        } else if (salarioBruto <= 7507.49) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (salarioBruto - 3856.94) * 0.14;
        } else {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (7507.49 - 3856.94) * 0.14;
        }
        return inss;
    }

    // Método para calcular o total de benefícios
    private double calcularTotalBeneficios(List<Beneficio> beneficios) {
        double total = 0.0;
        for (Beneficio beneficio : beneficios) {
            total += beneficio.getValor();
        }
        return total;
    }

    // Método para calcular as horas trabalhadas
    private double calcularHorasTrabalhadas(List<PontoEletrico> registrosPonto) {
        double horasTrabalhadas = 0.0;

        for (PontoEletrico registro : registrosPonto) {
            horasTrabalhadas += registro.getHorasTrabalhadas().toHours();
        }

        return horasTrabalhadas;
    }

    // Método para calcular horas extras
    private double calcularHorasExtras(List<PontoEletrico> registrosPonto) {
        double horasExtras = 0.0;

        for (PontoEletrico registro : registrosPonto) {
            if (registro.getHorasExtras().toHours() > 0) {
                horasExtras += registro.getHorasExtras().toHours();
            }
        }

        return horasExtras;
    }

    // Getters
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getMesReferencia() {
        return mesReferencia;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getInss() {
        return inss;
    }

    public double getFgts() {
        return fgts;
    }

    public double getTotalBeneficios() {
        return totalBeneficios;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }
}
