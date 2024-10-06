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

    // Construtor
    public FolhaPagamento(Funcionario funcionario, LocalDate mesReferencia) throws InvalidFolhaPagamentoException {
        if (funcionario == null) {
            throw new InvalidFolhaPagamentoException("Funcionário não pode ser nulo.");
        }

        if (mesReferencia == null) {
            throw new InvalidFolhaPagamentoException("A data de referência não pode ser nula.");
        }

        this.funcionario = funcionario;
        this.mesReferencia = mesReferencia;
        validarFuncionario(funcionario); // Validações iniciais
        calcularValores();
    }

    // Método para calcular os valores da folha de pagamento
    private void calcularValores() throws InvalidFolhaPagamentoException {
        // Validação do salário bruto
        this.salarioBruto = funcionario.getSalario();
        if (salarioBruto <= 0) {
            throw new InvalidFolhaPagamentoException("O salário bruto deve ser maior que zero.");
        }

        // Calcular INSS
        this.inss = calcularInss(salarioBruto);

        // Calcular FGTS como 8% do salário bruto
        this.fgts = salarioBruto * 0.08; // 8% de FGTS

        // Calcular o total de benefícios
        this.totalBeneficios = calcularTotalBeneficios(funcionario.getBeneficios());

        // Cálculo do salário líquido
        this.salarioLiquido = salarioBruto - inss + totalBeneficios;
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

        // Validação dos benefícios
        for (Beneficio beneficio : funcionario.getBeneficios()) {
            if (beneficio.getValor() < 0) {
                throw new InvalidFolhaPagamentoException(
                        "O valor do benefício " + beneficio.getNome() + " não pode ser negativo.");
            }
        }
    }

    // Método para calcular INSS (mantém o código anterior)
    private double calcularInss(double salarioBruto) {
        double inss = 0.0;
        if (salarioBruto <= 1302.00) {
            inss = salarioBruto * 0.075; // 7.5%
        } else if (salarioBruto <= 2571.29) {
            inss = 1302.00 * 0.075 + (salarioBruto - 1302.00) * 0.09; // 9%
        } else if (salarioBruto <= 3856.94) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (salarioBruto - 2571.29) * 0.12; // 12%
        } else if (salarioBruto <= 7507.49) {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (salarioBruto - 3856.94) * 0.14; // 14%
        } else {
            inss = 1302.00 * 0.075 + (2571.29 - 1302.00) * 0.09 + (3856.94 - 2571.29) * 0.12
                    + (7507.49 - 3856.94) * 0.14; // Teto do INSS
        }
        return inss;
    }

    // Método para calcular o total de benefícios
    private double calcularTotalBeneficios(List<Beneficio> beneficios) {
        double total = 0.0;
        for (Beneficio beneficio : beneficios) {
            total += beneficio.getValor(); // Adiciona o valor de cada benefício
        }
        return total;
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
