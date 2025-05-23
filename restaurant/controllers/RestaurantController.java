package br.com.gofood.gofood.restaurant.controllers;

import br.com.gofood.gofood.restaurant.dto.*;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.usecases.*;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final GetRestaurantUseCase getRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final UpdateRestaurantImageUseCase updateRestaurantImageUseCase;

    @GetMapping("/")
    public ResponseEntity<Page<GetAllRestaurantsResponseDTO>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<GetAllRestaurantsResponseDTO> result = getRestaurantUseCase.getAllRestaurants(pageable);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody @Valid RestaurantCollection restaurantCollection) throws IOException {
        var result = this.createRestaurantUseCase.execute(restaurantCollection, null);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/update/image/{restaurantId}")
    public ResponseEntity<Object> uploadImage(
            @PathVariable String restaurantId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        var result = this.updateRestaurantImageUseCase.execute(restaurantId, imageFile);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/delete")
    public ResponseEntity<DeleteRestaurantResponseDTO> delete(@Valid @RequestBody DeleteRestaurantRequestDTO request) {
        var result = this.deleteRestaurantUseCase.deleteRestaurantByCnpj(request.getCnpj(), request.getPassword());
        DeleteRestaurantResponseDTO response = new DeleteRestaurantResponseDTO(result.getCnpj(), result.getStatus(), "Restaurant is deleted", result.getDeletedAt());
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<UpdateRestaurantResponseDTO> update(@Valid @RequestBody UpdateRestaurantRequestDTO request) {
        var result = this.updateRestaurantUseCase.updateRestaurantByCnpj(request.getCnpj(), request.getName(), request.getPhone(), request.getAddressCollection(), request.getTypeFoodCollections());
        UpdateRestaurantResponseDTO response = new UpdateRestaurantResponseDTO(result.getName(), result.getPhone(), result.getAddressCollection(), result.getTypeFoodCollections());
        return ResponseEntity.ok().body(response);
    }
}
//S