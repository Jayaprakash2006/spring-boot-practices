package com.example.nxttrendz1.repository;

import java.util.ArrayList;
import com.example.nxttrendz1.model.Product;

public interface ProductRepository {

    ArrayList<Product> getProducts();
    Product addProduct(Product product);
    Product getProductById(int productId);
    Product updateProduct(int productId, Product product);
    void deleteMapping(int productId);

}