package br.com.gofood.gofood.exceptions;

public class RestaurantDeletedException extends RuntimeException{
    public RestaurantDeletedException() {
        super ("This restaurant is already deleted/deactivated from the system.");
    }
}
//S