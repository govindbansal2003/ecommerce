package com.ecommerce.service;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Entity.CartItem;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public CartItem addCartItem(Long userId, AddItemRequest req)throws ProductException;
    public Cart findUserCart(Long userId);
}
