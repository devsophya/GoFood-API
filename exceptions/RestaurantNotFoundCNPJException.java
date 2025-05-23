package br.com.gofood.gofood.exceptions;

public class RestaurantNotFoundCNPJException extends RuntimeException{
    public RestaurantNotFoundCNPJException() {
        super ("We couldn't find a restaurant with this CNPJ.");
    }
}
//S