package com.anthony.br.Controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.anthony.br.Connection.FuncionarioDAO;
import com.anthony.br.Models.Funcionario;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO; // Instância de FuncionarioDAO para acessar métodos de banco de dados

    // Construtor que inicializa a instância de FuncionarioDAO
    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    private List<Funcionario> funcionarios = new ArrayList<>(); // Lista de funcionários em memória

    // Método para cadastrar um funcionário
    public void cadastrarFuncionario(Funcionario funcionario) {
        try {
            funcionarioDAO.salvarFuncionario(funcionario); // Chama o DAO para salvar o funcionário no banco
        } catch (SQLException e) {
            e.printStackTrace(); // Tratamento de exceção para erros de SQL
        }
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        // Retorna a lista de funcionários através do método DAO
        return funcionarioDAO.listarFuncionarios();
    }

    // Método para atualizar os dados de um funcionário baseado no ID
    public void atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        // Percorre a lista de funcionários e encontra o funcionário com o ID correspondente
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
                // Atualiza os dados do funcionário
                funcionario.setNome(funcionarioAtualizado.getNome());
                funcionario.setCpf(funcionarioAtualizado.getCpf());
                funcionario.setEndereco(funcionarioAtualizado.getEndereco());
                funcionario.setTelefone(funcionarioAtualizado.getTelefone());
                funcionario.setCargo(funcionarioAtualizado.getCargo());
                funcionario.setDepartamento(funcionarioAtualizado.getDepartamento());
                funcionario.setSalario(funcionarioAtualizado.getSalario());
                funcionario.setDataContratacao(funcionarioAtualizado.getDataContratacao());
                funcionario.setDataNascimento(funcionarioAtualizado.getDataNascimento());
                funcionario.setEmail(funcionarioAtualizado.getEmail());
                funcionario.setContaBancaria(funcionarioAtualizado.getContaBancaria());
                funcionario.setBeneficios(funcionarioAtualizado.getBeneficios());
                break; // Sai do loop após encontrar e atualizar o funcionário
            }
        }
    }

    // Método para listar funcionários por departamento
    public List<Funcionario> listarFuncionariosPorDepartamento(String departamento) {
        // Filtra a lista de funcionários pelo departamento usando stream
        return funcionarios.stream()
                .filter(f -> f.getDepartamento().equalsIgnoreCase(departamento)) // Filtra pela correspondência exata do departamento
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }

    // Método para listar funcionários por cargo
    public List<Funcionario> listarFuncionariosPorCargo(String cargo) {
        // Filtra a lista de funcionários pelo cargo usando stream
        return funcionarios.stream()
                .filter(f -> f.getCargo().equalsIgnoreCase(cargo)) // Filtra pela correspondência exata do cargo
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }

    // Método para filtrar funcionários com mais de "anos" de tempo de serviço
    public List<Funcionario> filtrarPorTempoServico(int anos) {
        LocalDate hoje = LocalDate.now(); // Obtém a data atual
        // Filtra a lista de funcionários com base na data de contratação
        return funcionarios.stream()
                .filter(f -> f.getDataContratacao().isBefore(hoje.minusYears(anos))) // Verifica se o funcionário foi contratado há mais de "anos"
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }

    // Método para desativar (remover) um funcionário da lista baseado no ID
    public void desativarFuncionario(Long id) {
        // Remove o funcionário com o ID correspondente da lista
        funcionarios.removeIf(f -> f.getId().equals(id));
    }
}
