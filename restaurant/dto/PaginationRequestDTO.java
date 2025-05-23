package br.com.gofood.gofood.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequestDTO {
    private int page = 0;
    private int size = 10;
    private String sortBy = "name";
    private String direction = "asc";
}
//S
