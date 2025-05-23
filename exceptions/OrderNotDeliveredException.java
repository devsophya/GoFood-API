package br.com.gofood.gofood.exceptions;

public class OrderNotDeliveredException extends RuntimeException {

    public OrderNotDeliveredException(String message) {
        super(message);
    }
}