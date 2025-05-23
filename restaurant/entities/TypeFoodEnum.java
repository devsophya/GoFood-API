package br.com.gofood.gofood.restaurant.entities;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum TypeFoodEnum {
    HAMBURGUER("Hamburguer"),
    PIZZA("Pizza"),
    BRAZILIAN("Brazilian"),
    BARBECUE("Barbecue"),
    HEALTHY("Healthy"),
    JAPANESE("Japanese"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    ACAI("Açaí"),
    ARABIC("Arabic"),
    SNACKS("Snacks"),
    ITALIAN("Italian"),
    MEXICAN("Mexican"),
    CHINESE("Chinese");

    private final String displayName;

    TypeFoodEnum(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static TypeFoodEnum fromString(String value) {
        return Arrays.stream(TypeFoodEnum.values())
                .filter(type -> type.name().equalsIgnoreCase(value) || type.getDisplayName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid TypeFoodEnum: " + value));
    }

    public static boolean isValid(String value) {
        return Arrays.stream(TypeFoodEnum.values())
                .anyMatch(type -> type.name().equalsIgnoreCase(value) || type.getDisplayName().equalsIgnoreCase(value));
    }
}

