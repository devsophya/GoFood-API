package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.restaurant.entities.TypeFoodEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TypeFoodUseCase {
    public void validateTypeFood(List<String> types) {
        List<String> allowedTypes = Arrays.stream(TypeFoodEnum.values())
                .map(Enum::name)
                .toList();

        for (String type : types) {
            if (!allowedTypes.contains(type.toUpperCase())) {
                throw new IllegalArgumentException("Invalid food type: " + type +
                        ". Allowed types: " + allowedTypes);
            }
        }
    }
}
