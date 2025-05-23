package br.com.gofood.gofood.menu.controller;

import br.com.gofood.gofood.menu.dto.MenuRequestDTO;
import br.com.gofood.gofood.menu.dto.MenuResponseDTO;
import br.com.gofood.gofood.menu.dto.MenuUpdateRequestDTO;
import br.com.gofood.gofood.menu.usecases.DeleteMenuUseCase;
import br.com.gofood.gofood.menu.usecases.GetMenuUseCase;
import br.com.gofood.gofood.menu.usecases.CreateMenuUseCase;
import br.com.gofood.gofood.menu.usecases.UpdateMenuUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {

    private final CreateMenuUseCase createMenuUseCase;
    private final GetMenuUseCase getMenuUseCase;
    private final UpdateMenuUseCase updateMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

    @PostMapping("/create")
    public ResponseEntity<MenuResponseDTO> createDish(@Valid @RequestBody MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO createdDish = createMenuUseCase.createDish(menuRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @GetMapping("/")
    public ResponseEntity<Page<MenuResponseDTO>> getMenus(
            @RequestParam String restaurantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String name) {
        Page<MenuResponseDTO> menus = getMenuUseCase.getMenuByRestaurant(restaurantId, page, size, name);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/get/{idRestaurant}/{idFood}")
    public ResponseEntity<MenuResponseDTO> getFoodById(
            @PathVariable String idRestaurant,
            @PathVariable String idFood) {

        MenuResponseDTO menu = getMenuUseCase.getFoodById(idRestaurant, idFood);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> updateMenu(
            @PathVariable String menuId,
            @RequestBody MenuUpdateRequestDTO request) {

        MenuResponseDTO updatedMenu = updateMenuUseCase.execute(menuId, request);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/delete/{dishId}")
    public ResponseEntity<String> deleteDish(@PathVariable String dishId) {
        String responseMessage = deleteMenuUseCase.deleteDish(dishId);
        return ResponseEntity.ok(responseMessage);
    }
}
