package lk.ijse.fooddeliveryappbackend.service;

import lk.ijse.fooddeliveryappbackend.io.UserRequest;
import lk.ijse.fooddeliveryappbackend.io.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest request);
}
