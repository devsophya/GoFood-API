package br.com.gofood.gofood.exceptions;

public class RestaurantNotFoundAddressException extends RuntimeException{
    public RestaurantNotFoundAddressException() {
        super ("The restaurant's address could not be found.");
    }
}
//S