package br.com.gofood.gofood.restaurant.usecases;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.gofood.gofood.restaurant.dto.GetAllRestaurantsResponseDTO;
import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import br.com.gofood.gofood.restaurant.repositories.AddressRepository;
import br.com.gofood.gofood.restaurant.repositories.RestaurantRepository;
import br.com.gofood.gofood.restaurant.repositories.TypeFoodRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetRestaurantUseCase {

    private final AddressRepository addressRepository;
    private final TypeFoodRepository typeFoodRepository;
    private final RestaurantRepository restaurantRepository;
    private final MongoTemplate mongoTemplate;

    public Page<GetAllRestaurantsResponseDTO> getAllRestaurants(Pageable pageable) {

        Page<RestaurantCollection> restaurants = restaurantRepository.findAllProjected(pageable);

        return restaurants.map(restaurant -> new GetAllRestaurantsResponseDTO(
                restaurant.getImage(),
                restaurant.getName(),
                restaurant.getTypeFoodCollections() != null
                        ? restaurant.getTypeFoodCollections().stream()
                        .map(typeFood -> typeFood.getName().name())
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        ));
    }

    public List<GetAllRestaurantsResponseDTO> getAllRestaurantsWithAggregation() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").is(true)),
                Aggregation.project("image", "name", "typeFoodCollections")
        );

        AggregationResults<RestaurantCollection> results = mongoTemplate.aggregate(aggregation, "restaurant", RestaurantCollection.class);

        return results.getMappedResults().stream()
                .map(restaurant -> new GetAllRestaurantsResponseDTO(
                        restaurant.getImage(),
                        restaurant.getName(),
                        restaurant.getTypeFoodCollections() != null
                                ? restaurant.getTypeFoodCollections().stream()
                                .map(typeFood -> typeFood.getName().name())
                                .collect(Collectors.toList())
                                : new ArrayList<>()
                ))
                .collect(Collectors.toList());
    }
}
//S