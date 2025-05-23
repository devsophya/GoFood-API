package br.com.gofood.gofood.exceptions;

public class PromotionNotBelongToRestaurantException extends RuntimeException {
    public PromotionNotBelongToRestaurantException() {
        super("Promotion doesn't belong to this restaurant.");
    }
}
