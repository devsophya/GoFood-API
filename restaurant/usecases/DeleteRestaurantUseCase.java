package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.exceptions.RestaurantDeletedException;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundCNPJException;
import br.com.gofood.gofood.exceptions.RestaurantWrongPasswordException;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DeleteRestaurantUseCase {

    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    public RestaurantCollection deleteRestaurantByCnpj(String cnpj, String password) {
        RestaurantCollection restaurant = restaurantRepository.findByCnpj(cnpj)
                .orElseThrow(RestaurantNotFoundCNPJException::new);

        if (!restaurant.getStatus()) {
            throw new RestaurantDeletedException();
        }

        var isValidPassword = passwordEncoder.matches(password, restaurant.getPassword());
        if (isValidPassword) {
            restaurant.setStatus(false);
            restaurant.setDeletedAt(LocalDateTime.now());
        } else {
            throw new RestaurantWrongPasswordException();
        }

        return restaurantRepository.save(restaurant);

    }
}
//S