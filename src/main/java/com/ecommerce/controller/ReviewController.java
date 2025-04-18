package com.ecommerce.controller;

import com.ecommerce.Entity.Review;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.UserException;
import com.ecommerce.request.ReviewRequest;
import com.ecommerce.service.ReviewService;
import com.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final  UserService userService;
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user=userService.findUserProfileByJwt(jwt);
        System.out.println("product id "+req.getProductId()+" - "+req.getReview());
        Review review=reviewService.createReview(req, user);
        System.out.println("product review "+req.getReview());
        return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReviewHandler(@PathVariable Long productId){
        List<Review>reviews=reviewService.getAllReviews(productId);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}
