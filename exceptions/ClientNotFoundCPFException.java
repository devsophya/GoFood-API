package br.com.gofood.gofood.exceptions;

public class ClientNotFoundCPFException extends RuntimeException{
    public ClientNotFoundCPFException() {
        super ("We couldn't find a client with this CPF.");
    }
}
//S
