package lk.ijse.fooddeliveryappbackend.service;

import lk.ijse.fooddeliveryappbackend.entity.FoodEntity;
import lk.ijse.fooddeliveryappbackend.io.FoodRequest;
import lk.ijse.fooddeliveryappbackend.io.FoodResponse;
import lk.ijse.fooddeliveryappbackend.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{


    private final FoodRepository foodRepository;


    @Override
    public FoodResponse addFood(FoodRequest request, MultipartFile file) {
            FoodEntity foodEntity=  convertToEntity(request);


            if (file != null && !file.isEmpty()) {
            try {
                byte[] fileBytes = file.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(fileBytes);
                foodEntity.setImageBase64(base64Image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file", e);
            }
        }

        FoodEntity savedEntity = foodRepository.save(foodEntity);
        return convertToResponse(savedEntity);

    }
    private FoodEntity convertToEntity(FoodRequest request){
        return FoodEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }

    private FoodResponse convertToResponse(FoodEntity entity) {
        return FoodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .imageBase64(entity.getImageBase64())
                .build();
    }
}
