package br.com.gofood.gofood.promotion.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "promotions")
public class Promotion {
    @Id
    private String id;

    private String idRestaurant;
    private String name;
    private String promotionImage;
    private double discount;
    private LocalDate startPromotion;
    private LocalDate endPromotion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}