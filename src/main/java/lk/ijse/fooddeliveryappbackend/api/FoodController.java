package lk.ijse.fooddeliveryappbackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.fooddeliveryappbackend.io.FoodRequest;
import lk.ijse.fooddeliveryappbackend.io.FoodResponse;
import lk.ijse.fooddeliveryappbackend.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
@CrossOrigin("*")
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
    @GetMapping
    public List<FoodResponse> readFoods(){
        return foodService.readFoods();
    }
    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id){
            return foodService.readFood(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
        return ResponseEntity.ok("Food deleted");
    }
}
