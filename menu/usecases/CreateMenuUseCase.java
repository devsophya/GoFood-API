package br.com.gofood.gofood.menu.usecases;

import br.com.gofood.gofood.menu.dto.MenuRequestDTO;
import br.com.gofood.gofood.menu.dto.MenuResponseDTO;
import br.com.gofood.gofood.menu.entities.MenuCollection;
import br.com.gofood.gofood.menu.repositories.MenuRepository;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CreateMenuUseCase {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuResponseDTO createDish(MenuRequestDTO menuRequestDTO) {
        RestaurantCollection restaurant = restaurantRepository.findById(menuRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuCollection menu = MenuCollection.builder()
                .name(menuRequestDTO.getName())
                .description(menuRequestDTO.getDescription())
                .foodImage(menuRequestDTO.getFoodImage())
                .price(menuRequestDTO.getPrice())
                .foodCategory(menuRequestDTO.getFoodCategory())
                .available(menuRequestDTO.getAvailable())
                .restaurantId(restaurant.getId())
                .build();

        menu = menuRepository.save(menu);

        if (restaurant.getMenuCollection() == null) {
            restaurant.setMenuCollection(new ArrayList<>());
        }
        restaurant.getMenuCollection().add(menu);
        restaurantRepository.save(restaurant);

        return new MenuResponseDTO(menu.getId(), menu.getName(), menu.getDescription(), menu.getFoodImage(),
                menu.getPrice(), menu.getFoodCategory(), menu.isAvailable(), menu.getRestaurantId());
    }
}
//S

