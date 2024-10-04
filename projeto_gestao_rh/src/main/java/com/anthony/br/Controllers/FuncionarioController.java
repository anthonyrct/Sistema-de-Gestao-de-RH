package main.java.com.anthony.br.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import main.java.com.anthony.br.Models.Funcionario;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void atualizarFuncionario(int id, Funcionario funcionarioAtualizado) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionario.setNome(funcionarioAtualizado.getNome());
                funcionario.setCpf(funcionarioAtualizado.getCpf());
                funcionario.setEndereco(funcionarioAtualizado.getEndereco());
                funcionario.setTelefone(funcionarioAtualizado.getTelefone());
                funcionario.setCargo(funcionarioAtualizado.getCargo());
                funcionario.setDepartamento(funcionarioAtualizado.getDepartamento());
                funcionario.setSalario(funcionarioAtualizado.getSalario());
                funcionario.setDataAdmissao(funcionarioAtualizado.getDataAdmissao());
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
        Date hoje = new Date();
        return funcionarios.stream()
                .filter(f -> (hoje.getTime() - f.getDataAdmissao().getTime()) / (1000 * 60 * 60 * 24 * 365) > anos)
                .collect(Collectors.toList());
    }

    public void desativarFuncionario(int id) {
        funcionarios.removeIf(f -> f.getId() == id);
    }
}
