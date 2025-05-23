package br.com.gofood.gofood.exceptions;

public class RestaurantFoundException extends RuntimeException{
    public RestaurantFoundException() {
        super ("A restaurant with this CNPJ already exists.");
    }
}
//S