package lk.ijse.fooddeliveryappbackend.api;

import lk.ijse.fooddeliveryappbackend.io.UserRequest;
import lk.ijse.fooddeliveryappbackend.io.UserResponse;
import lk.ijse.fooddeliveryappbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request){
           return userService.registerUser(request);
    }
}
