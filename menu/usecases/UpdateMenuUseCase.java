package br.com.gofood.gofood.menu.usecases;

import br.com.gofood.gofood.menu.dto.MenuResponseDTO;
import br.com.gofood.gofood.menu.dto.MenuUpdateRequestDTO;
import br.com.gofood.gofood.menu.entities.MenuCollection;
import br.com.gofood.gofood.menu.repositories.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateMenuUseCase {

    private final MenuRepository menuRepository;

    public MenuResponseDTO execute(String menuId, MenuUpdateRequestDTO request) {
        MenuCollection existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Dish not found!"));

        if (request.getName() != null) existingMenu.setName(request.getName());
        if (request.getDescription() != null) existingMenu.setDescription(request.getDescription());
        if (request.getFoodImage() != null) existingMenu.setFoodImage(request.getFoodImage());
        if (request.getPrice() != null) existingMenu.setPrice(request.getPrice());
        if (request.getFoodCategory() != null) {
            existingMenu.setFoodCategory(MenuCollection.Category.valueOf(request.getFoodCategory().toUpperCase()));
        }
        if (request.getAvailable() != null) existingMenu.setAvailable(request.getAvailable());

        MenuCollection updatedMenu = menuRepository.save(existingMenu);

        return new MenuResponseDTO(
                updatedMenu.getId(),
                updatedMenu.getName(),
                updatedMenu.getDescription(),
                updatedMenu.getFoodImage(),
                updatedMenu.getPrice(),
                updatedMenu.getFoodCategory(),
                updatedMenu.isAvailable(),
                updatedMenu.getRestaurantId()
        );
    }
}
//S
