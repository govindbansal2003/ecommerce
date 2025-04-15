package com.ecommerce.service;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Entity.CartItem;
import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserService userService;

    public CartItemServiceImp(CartItemRepository cartItemRepository,
                              CartRepository cartRepository,
                              UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
       cartItem.setQuantity(1);
       cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
       cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

       CartItem savedCartItem = cartItemRepository.save(cartItem);
       return savedCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
       CartItem item = cartItemRepository.findCartItemById(id);
       User user = userService.findUserById(item.getUserId());
       if(user.getId().equals(userId)){
           item.setQuantity(cartItem.getQuantity());
           item.setPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
           item.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
       }
       return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userID){
       CartItem cartItem =cartItemRepository.isCartItemExist(cart, product, size, userID);
       return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem item = cartItemRepository.findCartItemById(cartItemId);
        User user = userService.findUserById(item.getUserId());

        User requstUser = userService.findUserById(userId);
        if(user.getId().equals(requstUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }else throw new UserException("You can throw another cart item");
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        if(opt.isPresent()){
             return opt.get();
        }
        throw new CartItemException("CartItem not found with this id "+cartItemId);
    }
}
