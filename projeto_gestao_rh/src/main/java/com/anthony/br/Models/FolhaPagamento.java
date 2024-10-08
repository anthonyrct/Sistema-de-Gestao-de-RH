package com.anthony.br.Models;

import java.time.LocalDate; // Importa a classe LocalDate para manipulação de datas
import java.util.List; // Importa a lista para armazenar benefícios e registros de ponto

import com.anthony.br.Exceptions.InvalidFolhaPagamentoException; // Importa a exceção personalizada

public class FolhaPagamento {
    private Funcionario funcionario; // Funcionário associado à folha de pagamento
    private LocalDate mesReferencia; // Data de referência para a folha de pagamento
    private double salarioBruto; // Salário bruto calculado
    private double inss; // Valor do INSS a ser descontado
    private double fgts; // Valor do FGTS a ser depositado
    private double totalBeneficios; // Total de benefícios do funcionário
    private double salarioLiquido; // Salário líquido após descontos e benefícios
    private List<PontoEletrico> registrosPonto; // Campo para registros de ponto

    private static final double CARGA_HORARIA_PADRAO = 160.0; // Carga horária mensal padrão
    private static final double HORAS_EXTRA_MULTIPLICADOR = 1.5; // Multiplicador de horas extras

    // Construtor
    public FolhaPagamento(Funcionario funcionario, LocalDate mesReferencia, List<PontoEletrico> registrosPonto)
            throws InvalidFolhaPagamentoException {
        // Validações para garantir que os parâmetros não sejam nulos
        if (funcionario == null) {
            throw new InvalidFolhaPagamentoException("Funcionário não pode ser nulo.");
        }

        if (mesReferencia == null) {
            throw new InvalidFolhaPagamentoException("A data de referência não pode ser nula.");
        }

        if (registrosPonto == null || registrosPonto.isEmpty()) {
            throw new InvalidFolhaPagamentoException("Registros de ponto não podem ser nulos ou vazios.");
        }

        this.funcionario = funcionario; // Atribui o funcionário
        this.mesReferencia = mesReferencia; // Atribui a data de referência
        this.registrosPonto = registrosPonto; // Atribui os registros de ponto
        validarFuncionario(funcionario); // Validações iniciais do funcionário
        calcularValores(); // Calcula os valores da folha de pagamento
    }

    // Método para calcular os valores da folha de pagamento
    private void calcularValores() throws InvalidFolhaPagamentoException {
        // Calcula as horas trabalhadas e extras a partir dos registros de ponto
        double horasTrabalhadas = calcularHorasTrabalhadas(registrosPonto);
        double horasExtras = calcularHorasExtras(registrosPonto);

        // Ajusta o salário bruto com base nas horas trabalhadas
        this.salarioBruto = (horasTrabalhadas / CARGA_HORARIA_PADRAO) * funcionario.getSalario();

        // Valida se o salário bruto é maior que zero
        if (salarioBruto <= 0) {
            throw new InvalidFolhaPagamentoException("O salário bruto deve ser maior que zero.");
        }

        // Calcula INSS e FGTS
        this.inss = calcularInss(salarioBruto);
        this.fgts = salarioBruto * 0.08; // 8% de FGTS

        // Calcula o total de benefícios do funcionário
        this.totalBeneficios = calcularTotalBeneficios(funcionario.getBeneficios());

        // Calcula o salário líquido (com base no salário bruto, INSS, e benefícios)
        this.salarioLiquido = salarioBruto - inss + totalBeneficios
                + (horasExtras * (funcionario.getSalario() / CARGA_HORARIA_PADRAO) * HORAS_EXTRA_MULTIPLICADOR);

        // Valida se o salário líquido é não negativo
        if (salarioLiquido < 0) {
            throw new InvalidFolhaPagamentoException("O salário líquido não pode ser negativo.");
        }
    }

    // Validação dos dados do funcionário
    private void validarFuncionario(Funcionario funcionario) throws InvalidFolhaPagamentoException {
        // Verifica se o salário do funcionário é maior que zero
        if (funcionario.getSalario() <= 0) {
            throw new InvalidFolhaPagamentoException("O salário do funcionário deve ser maior que zero.");
        }

        // Verifica se o CPF do funcionário é válido
        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            throw new InvalidFolhaPagamentoException("O CPF do funcionário é inválido.");
        }

        // Verifica se a data de nascimento do funcionário é válida
        if (funcionario.getDataNascimento() == null || funcionario.getDataNascimento().isAfter(LocalDate.now())) {
            throw new InvalidFolhaPagamentoException("A data de nascimento do funcionário é inválida.");
        }

        // Verifica se a data de contratação do funcionário é válida
        if (funcionario.getDataContratacao() == null || funcionario.getDataContratacao().isAfter(LocalDate.now())) {
            throw new InvalidFolhaPagamentoException("A data de contratação do funcionário é inválida.");
        }

        // Valida cada benefício do funcionário
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

        // Calcula o INSS com base nas faixas salariais
        if (salarioBruto <= 1302.00) {
            inss = salarioBruto * 0.075; // 7,5%
        } else if (salarioBruto <= 2571.29) {
            inss = 1302.00 * 0.075 + (salarioBruto - 1302.00) * 0.09; // 9%
        } else if (salarioBruto <= 3856.94) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (salarioBruto - 2571.29) * 0.12; // 12%
        } else if (salarioBruto <= 7507.49) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (salarioBruto - 3856.94) * 0.14; // 14%
        } else {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (7507.49 - 3856.94) * 0.14; // Até 14%
        }
        return inss; // Retorna o valor do INSS
    }

    // Método para calcular o total de benefícios
    private double calcularTotalBeneficios(List<Beneficio> beneficios) {
        double total = 0.0;

        // Soma todos os valores dos benefícios
        for (Beneficio beneficio : beneficios) {
            total += beneficio.getValor();
        }
        return total; // Retorna o total de benefícios
    }

    // Método para calcular as horas trabalhadas
    private double calcularHorasTrabalhadas(List<PontoEletrico> registrosPonto) {
        double horasTrabalhadas = 0.0;

        // Soma as horas trabalhadas a partir dos registros de ponto
        for (PontoEletrico registro : registrosPonto) {
            horasTrabalhadas += registro.getHorasTrabalhadas().toHours();
        }

        return horasTrabalhadas; // Retorna o total de horas trabalhadas
    }

    // Método para calcular horas extras
    private double calcularHorasExtras(List<PontoEletrico> registrosPonto) {
        double horasExtras = 0.0;

        // Soma as horas extras a partir dos registros de ponto
        for (PontoEletrico registro : registrosPonto) {
            if (registro.getHorasExtras().toHours() > 0) {
                horasExtras += registro.getHorasExtras().toHours();
            }
        }

        return horasExtras; // Retorna o total de horas extras
    }

    // Getters para acessar os atributos da classe
    public Funcionario getFuncionario() {
        return funcionario; // Retorna o funcionário associado à folha de pagamento
    }

    public LocalDate getMesReferencia() {
        return mesReferencia; // Retorna a data de referência da folha de pagamento
    }

    public double getSalarioBruto() {
        return salarioBruto; // Retorna o salário bruto calculado
    }

    public double getInss() {
        return inss; // Retorna o valor do INSS
    }

    public double getFgts() {
        return fgts; // Retorna o valor do FGTS
    }

    public double getTotalBeneficios() {
        return totalBeneficios; // Retorna o total de benefícios
    }

    public double getSalarioLiquido() {
        return salarioLiquido; // Retorna o salário líquido calculado
    }
}
