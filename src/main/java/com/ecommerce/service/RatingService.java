package com.ecommerce.service;

import com.ecommerce.Entity.Rating;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService{
    Rating createRating(RatingRequest req, User user)throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
