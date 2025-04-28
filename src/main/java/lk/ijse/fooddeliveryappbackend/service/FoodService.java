package lk.ijse.fooddeliveryappbackend.service;

import lk.ijse.fooddeliveryappbackend.io.FoodRequest;
import lk.ijse.fooddeliveryappbackend.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {
    FoodResponse addFood(FoodRequest request, MultipartFile file);
}
