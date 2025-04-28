package lk.ijse.fooddeliveryappbackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.fooddeliveryappbackend.io.FoodRequest;
import lk.ijse.fooddeliveryappbackend.io.FoodResponse;
import lk.ijse.fooddeliveryappbackend.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {

    private FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@RequestPart("food") String foodString,
                                @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper =new ObjectMapper();
        FoodRequest request;
        try {
           request = objectMapper.readValue(foodString,FoodRequest.class);
        }catch (JsonProcessingException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid JSON Format");

        }
        return foodService.addFood(request, file);
    }
}
