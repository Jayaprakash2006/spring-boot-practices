package com.example.nxttrendz1.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Column(name="productid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    @Column(name="productname")
    private String productName;

    @Column(name="price")
    private double price;

    public Product() {}

    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return this.productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName)  {
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}