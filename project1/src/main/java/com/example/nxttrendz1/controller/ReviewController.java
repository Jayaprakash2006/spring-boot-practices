package com.example.nxttrendz1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.service.ReviewJpaService;

@RestController
public class ReviewController {

    @Autowired
    ReviewJpaService service;

    @GetMapping("/products/reviews")
    public ArrayList<Review> getReviews() {
        return (ArrayList<Review>) service.getReviews();
    }

    @PostMapping("/products/reviews")
    public Review addReview(@RequestBody Review review) {
        return service.addReview(review);
    }

    @GetMapping("/products/reviews/{reviewId}")
    public Review getReviewById(@PathVariable("reviewId") int reviewId) {
        return service.getReviewById(reviewId);
    }

    @PutMapping("/products/reviews/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") int reviewId, @RequestBody Review review) {
        return service.updateReview(reviewId, review);
    }

    @DeleteMapping("/products/reviews/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") int reviewId) {
        service.deleteReview(reviewId);
    }

    @GetMapping("/reviews/{reviewId}/product")
    public Product getProductByReviewId(@PathVariable("reviewId") int reviewId) {
        return service.getProductByReviewId(reviewId);
    }
}