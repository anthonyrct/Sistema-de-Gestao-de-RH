package com.anthony.br.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.anthony.br.Models.Funcionario;

public class FuncionarioDAO {

    // Configurações de conexão com o banco de dados
    private final String url = "jdbc:postgresql://localhost:5432/gestao_rh"; // URL do banco de dados PostgreSQL
    private final String user = "postgres"; // Usuário do banco de dados
    private final String password = "postgres"; // Senha do banco de dados

    // Método para conectar ao banco de dados
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password); // Retorna a conexão usando DriverManager
    }

    // Método para salvar um funcionário no banco de dados
    public void salvarFuncionario(Funcionario funcionario) throws SQLException {
        // SQL para inserir um novo funcionário na tabela "funcionarios"
        String sql = "INSERT INTO funcionarios (nome, cpf, endereco, telefone, cargo, departamento, salario, data_admissao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Tenta estabelecer uma conexão e usar PreparedStatement para evitar SQL Injection
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Define os valores nos campos de inserção baseados nos atributos do objeto Funcionario
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getCargo());
            pstmt.setString(6, funcionario.getDepartamento());
            pstmt.setDouble(7, funcionario.getSalario());
            pstmt.setDate(8, java.sql.Date.valueOf(funcionario.getDataContratacao())); // Converte LocalDate para Date

            // Executa a inserção no banco de dados
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a exceção caso ocorra erro na inserção
        }
    }

    // Método para listar todos os funcionários cadastrados no banco de dados
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>(); // Lista para armazenar os funcionários
        String sql = "SELECT * FROM funcionarios"; // SQL para selecionar todos os registros da tabela "funcionarios"

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // Loop que percorre cada registro no ResultSet e cria objetos Funcionario
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setDataContratacao(rs.getDate("data_admissao").toLocalDate()); // Converte Date para LocalDate

                // Adiciona o objeto Funcionario à lista
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a exceção caso ocorra erro na consulta
        }
        return funcionarios; // Retorna a lista de funcionários
    }

    // Método para atualizar os dados de um funcionário no banco de dados
    public void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        // SQL para atualizar um registro na tabela "funcionarios"
        String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, endereco = ?, telefone = ?, cargo = ?, departamento = ?, "
                + "salario = ?, data_admissao = ? WHERE id = ?";

        // Tenta estabelecer uma conexão e usar PreparedStatement para atualizar os dados
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Define os novos valores para os campos a serem atualizados
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getCargo());
            pstmt.setString(6, funcionario.getDepartamento());
            pstmt.setDouble(7, funcionario.getSalario());
            pstmt.setDate(8, java.sql.Date.valueOf(funcionario.getDataContratacao())); // Converte LocalDate para Date
            pstmt.setLong(9, funcionario.getId()); // Define o ID do funcionário a ser atualizado

            // Executa a atualização no banco de dados
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a exceção caso ocorra erro na atualização
        }
    }

    // Método para excluir um funcionário do banco de dados pelo ID
    public void excluirFuncionario(int id) throws SQLException {
        // SQL para excluir um registro da tabela "funcionarios"
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        // Tenta estabelecer uma conexão e usar PreparedStatement para excluir o funcionário
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Define o ID do funcionário a ser excluído
            pstmt.executeUpdate(); // Executa a exclusão no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a exceção caso ocorra erro na exclusão
        }
    }
}
