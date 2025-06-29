package com.example.nxttrendz1.model;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {

    @Column(name="reviewid")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int reviewId;

    @Column(name="reviewcontent")
    private String reviewContent;

    @Column(name="rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name="productid")
    private Product product;

    public Review() {}

    public Review(int reviewId, String reviewContent, int rating, Product product) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.rating = rating;
        this.product = product;
    }

    public int getReviewId() {
        return this.reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return this.reviewContent;
    }
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getRating() {
        return this.rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return this.product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}