package br.com.gofood.gofood.menu.usecases;

import br.com.gofood.gofood.exceptions.DishNotFoundException;
import br.com.gofood.gofood.menu.repositories.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteMenuUseCase {

    private final MenuRepository menuRepository;

    public String deleteDish(String dishId) {
        if (!menuRepository.existsById(dishId)) {
            throw new DishNotFoundException();
        }

        menuRepository.deleteById(dishId);

        return "Plate with ID " + dishId + " has been successfully deleted!";
    }
}
