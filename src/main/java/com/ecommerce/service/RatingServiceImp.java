package com.ecommerce.service;

import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Rating;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.repository.RatingRepository;
import com.ecommerce.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImp implements RatingService{

    private final RatingRepository ratingRepository;
    private final ProductService productService;
    public RatingServiceImp(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
       Product product  = productService.findProductById(req.getProductId());
       Rating rating = new Rating();
       rating.setProduct(product);
       rating.setUser(user);
       rating.setRating(req.getRating());
       rating.setCreatedAt(LocalDateTime.now());
       return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
