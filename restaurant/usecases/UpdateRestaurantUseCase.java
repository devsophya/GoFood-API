package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.exceptions.RestaurantDeletedException;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundCNPJException;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundAddressException;
import br.com.gofood.gofood.exceptions.RestaurantNotFoundTypeFoodsException;
import br.com.gofood.gofood.restaurant.entities.AddressCollection;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.entities.TypeFoodCollection;
import br.com.gofood.gofood.restaurant.entities.TypeFoodEnum;
import br.com.gofood.gofood.restaurant.repositories.AddressRepository;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import br.com.gofood.gofood.restaurant.repositories.TypeFoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UpdateRestaurantUseCase {

    private final AddressRepository addressRepository;
    private final TypeFoodRepository typeFoodRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantCollection updateRestaurantByCnpj(String cnpj, String name, String phone,
                                                       AddressCollection address, List<TypeFoodCollection> typeFoods) {
        RestaurantCollection restaurant = restaurantRepository.findByCnpj(cnpj)
                .orElseThrow(RestaurantNotFoundCNPJException::new);

        if (!restaurant.getStatus()) {
            throw new RestaurantDeletedException();
        }

        if (address == null) {
            throw new RestaurantNotFoundAddressException();
        }

        AddressCollection updateAddressCollection = addressRepository.findById(restaurant.getAddressCollection().getId())
                .orElseThrow(RestaurantNotFoundAddressException::new);
        updateAddressCollection.setZipcode(address.getZipcode());
        updateAddressCollection.setCity(address.getCity());
        updateAddressCollection.setState(address.getState());
        updateAddressCollection.setNumber(address.getNumber());
        updateAddressCollection.setStreet(address.getStreet());
        updateAddressCollection.setNeighborhood(address.getNeighborhood());

        updateAddressCollection = addressRepository.save(updateAddressCollection);
        restaurant.setAddressCollection(updateAddressCollection);

        List<TypeFoodCollection> updatedTypeFoods = typeFoods.stream()
                .map(typeFood -> {
                    if (typeFood.getName() == null) {
                        throw new RestaurantNotFoundTypeFoodsException();
                    }
                    String typeFoodName = typeFood.getName().name();
                    return typeFoodRepository.findByName(typeFoodName)
                            .orElseGet(() -> {
                                TypeFoodCollection newTypeFood = new TypeFoodCollection();
                                newTypeFood.setName(TypeFoodEnum.valueOf(typeFoodName));
                                return typeFoodRepository.save(newTypeFood);
                            });
                })
                .collect(Collectors.toList());


        restaurant.setName(name);
        restaurant.setPhone(phone);
        restaurant.setAddressCollection(updateAddressCollection);
        restaurant.setTypeFoodCollections(updatedTypeFoods);

        return restaurantRepository.save(restaurant);

    }
}
//S