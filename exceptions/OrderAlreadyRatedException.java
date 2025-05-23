package br.com.gofood.gofood.exceptions;

public class OrderAlreadyRatedException extends RuntimeException {

    public OrderAlreadyRatedException(String message) {
        super(message);
    }
}