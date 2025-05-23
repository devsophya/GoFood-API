package br.com.gofood.gofood.menu.repositories;

import br.com.gofood.gofood.menu.entities.MenuCollection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<MenuCollection, String> {
    Page<MenuCollection> findByRestaurantId(String restaurantId, Pageable pageable);
    Page<MenuCollection> findByRestaurantIdAndNameContainingIgnoreCase(String restaurantId, String name, Pageable pageable);
}
//S