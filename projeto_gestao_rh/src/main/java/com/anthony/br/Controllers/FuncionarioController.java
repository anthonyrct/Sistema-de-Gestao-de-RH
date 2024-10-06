package com.anthony.br.Controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.anthony.br.Connection.FuncionarioDAO;
import com.anthony.br.Models.Funcionario;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void cadastrarFuncionario(Funcionario funcionario) {
        try {
            funcionarioDAO.salvarFuncionario(funcionario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar funcionários
    public List<Funcionario> listarFuncionarios() {
        
        return funcionarioDAO.listarFuncionarios();
    }

    public void atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
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
                break;
            }
        }
    }

    public List<Funcionario> listarFuncionariosPorDepartamento(String departamento) {
        return funcionarios.stream()
                .filter(f -> f.getDepartamento().equalsIgnoreCase(departamento))
                .collect(Collectors.toList());
    }

    public List<Funcionario> listarFuncionariosPorCargo(String cargo) {
        return funcionarios.stream()
                .filter(f -> f.getCargo().equalsIgnoreCase(cargo))
                .collect(Collectors.toList());
    }

    public List<Funcionario> filtrarPorTempoServico(int anos) {
        LocalDate hoje = LocalDate.now();
        return funcionarios.stream()
                .filter(f -> f.getDataContratacao().isBefore(hoje.minusYears(anos)))
                .collect(Collectors.toList());
    }

    public void desativarFuncionario(Long id) {
        funcionarios.removeIf(f -> f.getId().equals(id));
    }
}
