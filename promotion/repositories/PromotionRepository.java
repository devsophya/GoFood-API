package br.com.gofood.gofood.promotion.repositories;

import br.com.gofood.gofood.promotion.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {
    Page<Promotion> findByIdRestaurant(String idRestaurant, Pageable pageable);
    Page<Promotion> findByIdRestaurantAndNameContainingIgnoreCase(String idRestaurant, String name, Pageable pageable);

    @Query("{ 'endPromotion' : { $gte : ?0 } }")
    List<Promotion> findAllValidPromotions(LocalDate currentDate);
}


