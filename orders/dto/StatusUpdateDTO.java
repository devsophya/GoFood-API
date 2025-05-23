package br.com.gofood.gofood.orders.dto;

import br.com.gofood.gofood.orders.enums.StatusOrderEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateDTO {
    private StatusOrderEnum statusUpdate;
}
