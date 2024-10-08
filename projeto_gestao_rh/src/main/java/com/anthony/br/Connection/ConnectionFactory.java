package com.anthony.br.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // URL de conexão com o banco de dados PostgreSQL.
    // Sintaxe: jdbc:postgresql://<host>:<port>/<database>
    private static final String URL = "jdbc:postgresql://localhost:5432/gestao_rh";
    
    // Usuário do banco de dados PostgreSQL.
    private static final String USER = "postgres";
    
    // Senha do banco de dados PostgreSQL.
    private static final String PASSWORD = "postgres";

    /**
     * Método estático para obter uma conexão com o banco de dados PostgreSQL.
     * 
     * @return Connection - Objeto de conexão com o banco de dados.
     * @throws SQLException - Caso ocorra um erro ao tentar conectar.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Tenta estabelecer uma conexão com o banco de dados usando o DriverManager.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Caso ocorra uma exceção (falha na conexão), lança uma RuntimeException
            // com uma mensagem explicativa e inclui a causa original da SQLException.
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
