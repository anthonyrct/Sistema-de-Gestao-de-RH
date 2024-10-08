package com.anthony.br.Exceptions;

// Classe personalizada de exceção que estende a classe Exception
public class InvalidFolhaPagamentoException extends Exception {

    // Construtor que recebe uma mensagem de erro e a passa para a superclasse
    public InvalidFolhaPagamentoException(String message) {
        super(message); // Chama o construtor da classe pai (Exception) com a mensagem fornecida
    }
}