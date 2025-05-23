package br.com.gofood.gofood.menu.usecases;

import br.com.gofood.gofood.menu.dto.MenuResponseDTO;
import br.com.gofood.gofood.menu.entities.MenuCollection;
import br.com.gofood.gofood.menu.repositories.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class GetMenuUseCase {

    private final MenuRepository menuRepository;

    public Page<MenuResponseDTO> getMenuByRestaurant(String restaurantId, int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Page<MenuCollection> menuPage = (name != null && !name.isEmpty())
                ? menuRepository.findByRestaurantIdAndNameContainingIgnoreCase(restaurantId, name, pageable)
                : menuRepository.findByRestaurantId(restaurantId, pageable);

        return menuPage.map(this::toDto);
    }

    public MenuResponseDTO getFoodById(String restaurantId, String foodId) {
        MenuCollection menu = menuRepository.findById(foodId)
                .filter(m -> m.getRestaurantId().equals(restaurantId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found"));

        return toDto(menu);
    }

    private MenuResponseDTO toDto(MenuCollection menu) {
        return new MenuResponseDTO(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getFoodImage(),
                menu.getPrice(),
                menu.getFoodCategory(),
                menu.isAvailable(),
                menu.getRestaurantId()
        );
    }
}
//S
