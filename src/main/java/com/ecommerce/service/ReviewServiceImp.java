package com.ecommerce.service;

import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Review;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ReviewRepository;
import com.ecommerce.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public ReviewServiceImp(ReviewRepository reviewRepository,ProductService productService){
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
       Product product = productService.findProductById(req.getProductId());
       Review review = new Review();
       review.setUser(user);
       review.setProduct(product);
       review.setReview(req.getReview());
       review.setCreatedAt(LocalDateTime.now());
       return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
