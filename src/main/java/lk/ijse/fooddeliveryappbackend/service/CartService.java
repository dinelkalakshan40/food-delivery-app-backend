package lk.ijse.fooddeliveryappbackend.service;

import lk.ijse.fooddeliveryappbackend.io.CartRequest;
import lk.ijse.fooddeliveryappbackend.io.CartResponse;

public interface CartService {
   CartResponse addToCart(CartRequest request);

   CartResponse getCart();
}
