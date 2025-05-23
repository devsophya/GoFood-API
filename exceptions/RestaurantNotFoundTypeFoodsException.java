package br.com.gofood.gofood.exceptions;

public class RestaurantNotFoundTypeFoodsException extends RuntimeException{
    public RestaurantNotFoundTypeFoodsException() {
        super ("The type of food not informed.");
    }
}
//S