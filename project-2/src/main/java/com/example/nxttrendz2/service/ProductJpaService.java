package com.example.nxttrendz2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.ProductRepository;
import com.example.nxttrendz2.repository.ProductJpaRepository;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    ProductJpaRepository db;

    @Autowired
    CategoryJpaRepository category_db;

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) db.findAll();
    }

    public Product addProduct(Product product) {
        try {
            int categoryId = product.getCategory().getId();
            product.setCategory(category_db.findById(categoryId).get());
            return db.save(product);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Product getProductById(int productId) {
        try {
            Product product = db.findById(productId).get();
            return product;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Product updateProduct(int productId, Product product) {
        try {
            Product ext = db.findById(productId).get();
            if(product.getName() != null) ext.setName(product.getName());
            if(product.getDescription() != null) ext.setDescription(product.getDescription());
            if(product.getPrice() != 0.0) ext.setPrice(product.getPrice());
            if(product.getCategory() != null) {
                int categoryId = product.getCategory().getId();
                Category category = category_db.findById(categoryId).get();
                ext.setCategory(category);
            }

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteProduct(int productId) {
        try {
            db.deleteById(productId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Category getCategoryByProduct(int productId) {
        try {
            Product product = db.findById(productId).get();
            Category category = product.getCategory();
            return category;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}