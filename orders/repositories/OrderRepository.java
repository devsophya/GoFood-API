package br.com.gofood.gofood.orders.repositories;

import br.com.gofood.gofood.orders.entities.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByIdClientOrderByCreatedAtDesc(String idClient);
    List<Order> findByIdRestaurantOrderByCreatedAtDesc(String restaurantId);
    Optional<Order> findById(ObjectId id);

    @Query("{ 'itemOrder.idFoodMenu' : ?0, 'review' : { $ne: null } }")
    List<Order> findOrdersByFoodIdWithReview(String idFoodMenu);
}
