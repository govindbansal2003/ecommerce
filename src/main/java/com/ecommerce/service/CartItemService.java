package com.ecommerce.service;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Entity.CartItem;
import com.ecommerce.Entity.Product;
import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import org.springframework.stereotype.Repository;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException, UserException;
    CartItem isCartItemExist(Cart cart, Product product, String size, Long userID);
    void removeCartItem(Long userId,Long cartItemId)throws CartItemException, UserException;
    CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
