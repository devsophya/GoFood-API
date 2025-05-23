package br.com.gofood.gofood.exceptions;

public class LimitAddressExceededException extends RuntimeException{
    public LimitAddressExceededException() {
        super("The customer already has a maximum limit of 5 addresses.");
    }
}
//S
