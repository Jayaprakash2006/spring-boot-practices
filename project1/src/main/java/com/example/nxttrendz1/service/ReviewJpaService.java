package com.example.nxttrendz1.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.repository.ReviewRepository;
import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ProductJpaRepository;

@Service
public class ReviewJpaService implements ReviewRepository {

    @Autowired
    ReviewJpaRepository db;
    
    @Autowired
    ProductJpaRepository product_db;

    public ArrayList<Review> getReviews() {
        return (ArrayList<Review>) db.findAll();
    }

    public Review addReview(Review review) {
        Product product = review.getProduct();
        int productId = product.getProductId();
        Product ext_prod = product_db.findById(productId).get();
        review.setProduct(ext_prod);
        return db.save(review);
    }

    public Review getReviewById(int reviewId) {
        try {
            Review review = db.findById(reviewId).get();
            return review;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Review updateReview(int reviewId, Review review) {
        try {
            Review ext = db.findById(reviewId).get();
            if(review.getReviewContent() != null) ext.setReviewContent(review.getReviewContent());
            if(review.getRating() != 0) ext.setRating(review.getRating());
            if(review.getProduct() != null) {
                Product product = review.getProduct();
                int productId = product.getProductId();
                Product ext_prod = product_db.findById(productId).get();
                ext.setProduct(ext_prod);
            }

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteReview(int reviewId) {
       try {
        db.deleteById(reviewId);
       }
       catch(Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Product getProductByReviewId(int reviewId) {
        try {
            Review review = db.findById(reviewId).get();
            Product product = review.getProduct();
            return product;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}