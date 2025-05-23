package br.com.gofood.gofood.forgottenPassword.usecases;

import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundCNPJException;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestaurantNewPasswordUseCase {
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;

    public RestaurantNewPasswordUseCase(RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(String email, String code, String newPassword) {
        RestaurantCollection restaurantCollection = restaurantRepository.findByEmail(email)
                .orElseThrow(() -> new RestaurantNotFoundCNPJException());

        if (!code.equals(restaurantCollection.getResetCode())) {
            throw new RuntimeException("Invalid code.");
        }

        if (!isStrongPassword(newPassword)) {
            throw new RuntimeException("Weak password. Must have at least 8 characters, uppercase, lowercase, number and symbol.");
        }

        restaurantCollection.setPassword(passwordEncoder.encode(newPassword));
        restaurantCollection.setResetCode(null);
        restaurantRepository.save(restaurantCollection);
    }

    private boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}

