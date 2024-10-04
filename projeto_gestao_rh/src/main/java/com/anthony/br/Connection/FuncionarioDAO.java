package main.java.com.anthony.br.Connection;

import main.java.com.anthony.br.Models.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private final String url = "jdbc:postgresql://localhost:5432/seu_banco_de_dados"; // Ajuste o nome do seu banco
    private final String user = "seu_usuario";
    private final String password = "sua_senha";

    // Método para conectar ao banco de dados
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Método para salvar um funcionário no banco de dados
    public void salvarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, endereco, telefone, cargo, departamento, salario, data_admissao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getCargo());
            pstmt.setString(6, funcionario.getDepartamento());
            pstmt.setDouble(7, funcionario.getSalario());
            pstmt.setDate(8, new java.sql.Date(funcionario.getDataAdmissao().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setDataAdmissao(rs.getDate("data_admissao"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    // Método para atualizar um funcionário no banco de dados
    public void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, endereco = ?, telefone = ?, cargo = ?, departamento = ?, "
                + "salario = ?, data_admissao = ? WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getCargo());
            pstmt.setString(6, funcionario.getDepartamento());
            pstmt.setDouble(7, funcionario.getSalario());
            pstmt.setDate(8, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
            pstmt.setInt(9, funcionario.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um funcionário
    public void excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
