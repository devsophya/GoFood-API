package br.com.gofood.gofood.orders.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private int stars;
    private String comment;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}