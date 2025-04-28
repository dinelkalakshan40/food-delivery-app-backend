package lk.ijse.fooddeliveryappbackend.repository;

import lk.ijse.fooddeliveryappbackend.entity.FoodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<FoodEntity,String> {
}
