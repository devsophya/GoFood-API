package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.exceptions.RestaurantFoundException;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.AddressRepository;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import br.com.gofood.gofood.restaurant.repositories.TypeFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final TypeFoodRepository typeFoodRepository;
    private final CloudinaryUseCase cloudinaryUseCase;
    private final TypeFoodUseCase typeFoodUseCase;
    private final PasswordEncoder passwordEncoder;

        public CreateRestaurantUseCase(CloudinaryUseCase cloudinaryClientUseCase, RestaurantRepository restaurantRepository, AddressRepository addressRepository, TypeFoodRepository typeFoodRepository, TypeFoodUseCase typeFoodUseCase, PasswordEncoder passwordEncoder) {
            this.cloudinaryUseCase = cloudinaryClientUseCase;
            this.restaurantRepository = restaurantRepository;
            this.addressRepository = addressRepository;
            this.typeFoodRepository = typeFoodRepository;
            this.typeFoodUseCase = typeFoodUseCase;
            this.passwordEncoder = passwordEncoder;
        }

    public RestaurantCollection execute(RestaurantCollection restaurantCollection, MultipartFile imageFile) throws IOException {
        this.restaurantRepository.findByCnpj(restaurantCollection.getCnpj())
            .ifPresent((cnpj) -> {
                throw new RestaurantFoundException();
            });

        if (imageFile != null && !imageFile.isEmpty())  {
            String imageUrl = cloudinaryUseCase.uploadImage(imageFile);
            restaurantCollection.setImage(imageUrl);
        }

        if (restaurantCollection.getAddressCollection() != null) {
            var savedAddress = addressRepository.save(restaurantCollection.getAddressCollection());
            restaurantCollection.setAddressCollection(savedAddress);
        }

        if (restaurantCollection.getTypeFoodCollections() != null && !restaurantCollection.getTypeFoodCollections().isEmpty()) {
            List<String> typeFoodNames = restaurantCollection.getTypeFoodCollections()
                    .stream()
                    .map(typeFood -> typeFood.getName().toString())
                    .collect(Collectors.toList());

           typeFoodUseCase.validateTypeFood(typeFoodNames);

            var savedTypes = typeFoodRepository.saveAll(restaurantCollection.getTypeFoodCollections());
            restaurantCollection.setTypeFoodCollections(savedTypes);
        }

        var password = passwordEncoder.encode(restaurantCollection.getPassword());
        restaurantCollection.setPassword(password);

        return this.restaurantRepository.save(restaurantCollection);
    }
}
//S