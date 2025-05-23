package br.com.gofood.gofood.favorites.controllers;

import br.com.gofood.gofood.favorites.dto.CreateFoodFavoriteRequestDTO;
import br.com.gofood.gofood.favorites.dto.CreateRestaurantFavoriteRequestDTO;
import br.com.gofood.gofood.favorites.dto.FavoritesResponseDTO;
import br.com.gofood.gofood.favorites.usecases.FavoriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/{clientId}/favorites")
@PreAuthorize("hasRole('CLIENT')")
@RequiredArgsConstructor
public class FoodFavoriteController {

    private final FavoriteUseCase favoriteUseCase;

    @PostMapping("/menu/create")
    public ResponseEntity<String> createFoodFavorite(@PathVariable String clientId,
                                                     @RequestBody CreateFoodFavoriteRequestDTO request) {
        favoriteUseCase.addFoodFavorite(clientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Prato favorito adicionado com sucesso!");
    }

    @PostMapping("/restaurant/create")
    public ResponseEntity<String> createRestaurantFavorite(@PathVariable String clientId,
                                                           @RequestBody CreateRestaurantFavoriteRequestDTO request) {
        favoriteUseCase.addRestaurantFavorite(clientId, request.getIdRestaurant());
        return ResponseEntity.ok("Restaurante favorito adicionado com sucesso!");
    }

    @GetMapping("/get")
    public ResponseEntity<FavoritesResponseDTO> getFavorites(@PathVariable String clientId) {
        FavoritesResponseDTO favorites = favoriteUseCase.getFavorites(clientId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/restaurant/{idRestaurant}")
    public ResponseEntity<String> deleteRestaurantFavorite(@PathVariable String clientId,
                                                           @PathVariable String idRestaurant) {
        favoriteUseCase.deleteRestaurantFavorite(clientId, idRestaurant);
        return ResponseEntity.ok("Restaurante favorito deletado com sucesso.");
    }

    @DeleteMapping("/menu/{idFood}")
    public ResponseEntity<String> deleteFoodFavorite(@PathVariable String clientId,
                                                     @PathVariable String idFood) {
        favoriteUseCase.deleteFoodFavorite(clientId, idFood);
        return ResponseEntity.ok("Prato favorito deletado com sucesso.");
    }
}