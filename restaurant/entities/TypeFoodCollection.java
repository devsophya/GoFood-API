package br.com.gofood.gofood.restaurant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "types_food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeFoodCollection {
    @Id
    private String id;

    @NotNull(message = "The type of food is mandatory.")
    @Indexed(unique = true)
    private TypeFoodEnum name;

    public TypeFoodCollection(TypeFoodEnum name) {
        this.name = name;
    }
}
//S
