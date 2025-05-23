package br.com.gofood.gofood.exceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException() {
        super ("Dish not found.");
    }
}
//S