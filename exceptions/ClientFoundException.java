package br.com.gofood.gofood.exceptions;

public class ClientFoundException extends RuntimeException{
    public ClientFoundException() {
        super ("A client with this CPF already exists.");
    }
}
