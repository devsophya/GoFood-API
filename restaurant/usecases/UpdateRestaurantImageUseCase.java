package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.exceptions.RestaurantNotFoundCNPJException;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class UpdateRestaurantImageUseCase {

    private final RestaurantRepository restaurantRepository;
    private final CloudinaryUseCase cloudinaryUseCase;

    public RestaurantCollection execute(String restaurantId, MultipartFile imageFile) throws IOException {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundCNPJException());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = cloudinaryUseCase.uploadImage(imageFile);
            restaurant.setImage(imageUrl);
            restaurantRepository.save(restaurant);
        }

        return restaurant;
    }
}
//S