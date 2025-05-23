package br.com.gofood.gofood.address.dto;

import br.com.gofood.gofood.address.collections.AddressCollection;
import lombok.Getter;

@Getter
public class AddressResponseDTO {

    private String id;
    private String nickname;
    private String zipcode;
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;

    public AddressResponseDTO(AddressCollection address) {
        this.id = address.getId();
        this.nickname = address.getNickname();
        this.zipcode = address.getZipcode();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.state = address.getState();
    }
}
//S