package br.com.gofood.gofood.exceptions;

public class RestaurantWrongPasswordException extends RuntimeException{
    public RestaurantWrongPasswordException() {
        super ("Your password is incorrect");
    }
}
//S