package br.com.gofood.gofood.exceptions;

public class ClientWrongPasswordException extends RuntimeException{
    public ClientWrongPasswordException() {
        super ("Your password is incorrect");
    }
}
//S
