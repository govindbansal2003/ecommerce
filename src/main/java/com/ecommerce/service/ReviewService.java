package com.ecommerce.service;

import com.ecommerce.Entity.Review;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest req, User user)throws ProductException;
    List<Review> getAllReviews(Long productId);
}
