package br.com.gofood.gofood.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User already exists.");
    }
}
//S