package br.com.gofood.gofood.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateRequestDTO {
    private String name;
    private String description;
    private String foodImage;
    private Double price;
    private String foodCategory;
    private Boolean available;
}
