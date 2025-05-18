package lk.ijse.fooddeliveryappbackend.service;

import lk.ijse.fooddeliveryappbackend.entity.FoodEntity;
import lk.ijse.fooddeliveryappbackend.io.FoodRequest;
import lk.ijse.fooddeliveryappbackend.io.FoodResponse;
import lk.ijse.fooddeliveryappbackend.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<FoodResponse> readFoods() {
        List<FoodEntity> list =foodRepository.findAll();
        return list.stream().map(object ->convertToResponse(object)).collect(Collectors.toList());
    }

    @Override
    public FoodResponse readFood(String id) {
        FoodEntity existingFood= foodRepository.findById(id).orElseThrow(()->new RuntimeException("Food not found"));
        return convertToResponse(existingFood);
    }

    @Override
    public void deleteFood(String id) {
        if(!foodRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id: " + id);
        }
        foodRepository.deleteById(id);
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
