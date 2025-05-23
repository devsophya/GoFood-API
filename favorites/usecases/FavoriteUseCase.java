package br.com.gofood.gofood.favorites.usecases;

import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.favorites.dto.*;
import br.com.gofood.gofood.favorites.entities.FoodFavoritesCollection;
import br.com.gofood.gofood.favorites.entities.RestaurantFavoriteCollection;
import br.com.gofood.gofood.favorites.repositories.FoodFavoriteRepository;
import br.com.gofood.gofood.favorites.repositories.RestaurantFavoriteRepository;
import br.com.gofood.gofood.menu.repositories.MenuRepository;
import br.com.gofood.gofood.orders.entities.Order;
import br.com.gofood.gofood.orders.repositories.OrderRepository;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteUseCase {

    private final FoodFavoriteRepository foodFavoriteRepository;
    private final RestaurantFavoriteRepository restaurantFavoriteRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public void addFoodFavorite(String clientId, CreateFoodFavoriteRequestDTO request) {
        FoodFavoritesCollection favorite = FoodFavoritesCollection.builder()
                .clientId(clientId)
                .idFoodMenu(request.getIdFoodMenu())
                .idRestaurant(request.getIdRestaurant())
                .build();
        favorite = foodFavoriteRepository.save(favorite);
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (client.getFoodFavorites() == null) {
            client.setFoodFavorites(new ArrayList<>());
        }
        client.getFoodFavorites().add(favorite);

        clientRepository.save(client);
    }

    public void addRestaurantFavorite(String clientId, String restaurantFavoriteId) {
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (client.getRestaurantFavoriteIds() == null) {
            client.setRestaurantFavoriteIds(new ArrayList<>());
        }
        client.getRestaurantFavoriteIds().add(restaurantFavoriteId);
        clientRepository.save(client);
        RestaurantFavoriteCollection restaurantFavorite = RestaurantFavoriteCollection.builder()
                .clientId(clientId)
                .idRestaurant(restaurantFavoriteId)
                .build();

        restaurantFavoriteRepository.save(restaurantFavorite);
    }

    public FavoritesResponseDTO getFavorites(String clientId) {
        List<RestaurantFavoriteResponseDTO> restaurantFavorites = restaurantFavoriteRepository.findAllByClientId(clientId)
                .stream()
                .map(r -> {
                    var restaurant = restaurantRepository.findById(r.getIdRestaurant())
                            .orElse(null);
                    return RestaurantFavoriteResponseDTO.builder()
                            .id(r.getId())
                            .idRestaurant(r.getIdRestaurant())
                            .name(restaurant != null ? restaurant.getName() : null)
                            .image(restaurant != null ? restaurant.getImage() : null)
                            .description(restaurant != null ? restaurant.getDescription() : null)
                            .build();
                })
                .collect(Collectors.toList());

        List<FoodFavoriteResponseDTO> foodFavorites = foodFavoriteRepository.findAllByClientId(clientId)
                .stream()
                .map(f -> {
                    var foodMenu = menuRepository.findById(f.getIdFoodMenu())
                            .orElse(null);
                    return FoodFavoriteResponseDTO.builder()
                            .id(f.getId())
                            .idFoodMenu(f.getIdFoodMenu())
                            .idRestaurant(f.getIdRestaurant())
                            .name(foodMenu != null ? foodMenu.getName() : null)
                            .description(foodMenu != null ? foodMenu.getDescription() : null)
                            .price(foodMenu != null ? foodMenu.getPrice() : null)
                            .averageRating(
                                    calculateAverageRatingForFood(f.getIdFoodMenu())
                            )
                            .build();
                })
                .collect(Collectors.toList());

        return FavoritesResponseDTO.builder()
                .restaurantFavorites(restaurantFavorites)
                .foodFavorites(foodFavorites)
                .build();
    }

    public void deleteFoodFavorite(String clientId, String idFoodFavorite) {
        foodFavoriteRepository.findById(idFoodFavorite)
                .ifPresentOrElse(foodFavorite -> {
                    if (!foodFavorite.getClientId().equals(clientId)) {
                        throw new RuntimeException("Favorito não pertence ao cliente informado");
                    }
                    foodFavoriteRepository.delete(foodFavorite);
                    ClientCollection client = clientRepository.findById(clientId)
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

                    if (client.getFoodFavorites() != null) {
                        client.getFoodFavorites().removeIf(fav -> fav.getId().equals(foodFavorite.getId()));
                    }

                    clientRepository.save(client);
                }, () -> {
                    throw new RuntimeException("Prato favorito não encontrado");
                });
    }

    public void deleteRestaurantFavorite(String clientId, String idRestaurant) {
        restaurantFavoriteRepository.findByClientIdAndIdRestaurant(clientId, idRestaurant)
                .ifPresent(restaurantFavoriteRepository::delete);
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (client.getRestaurantFavoriteIds() != null) {
            client.getRestaurantFavoriteIds().remove(idRestaurant);
        }
        clientRepository.save(client);
    }

    public Double calculateAverageRatingForFood(String idFoodMenu) {
        List<Order> orders = orderRepository.findOrdersByFoodIdWithReview(idFoodMenu);

        if (orders.isEmpty()) {
            return 0.0;
        }

        return orders.stream()
                .mapToInt(order -> order.getReview().getStars())
                .average()
                .orElse(0.0);
    }
}
