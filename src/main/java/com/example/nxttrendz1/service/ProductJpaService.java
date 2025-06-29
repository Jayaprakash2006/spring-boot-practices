package com.example.nxttrendz1.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.repository.ProductRepository;
import com.example.nxttrendz1.repository.ProductJpaRepository;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    ProductJpaRepository db;

    public ArrayList<Product> getProducts() {
        List<Product> list = db.findAll();
        ArrayList<Product> arr_list = new ArrayList<>(list);
        return arr_list;
    }

    public Product addProduct(Product product) {
        return db.save(product);
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
            if(product.getProductName() != null) ext.setProductName(product.getProductName());
            if(product.getPrice() != 0.0) ext.setPrice(product.getPrice());

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteMapping(int productId) {
        try {
        db.deleteById(productId);
       }
       catch(Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}