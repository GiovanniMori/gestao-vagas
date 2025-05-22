package org.example.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super("User already exists: " + message);
    }
}
