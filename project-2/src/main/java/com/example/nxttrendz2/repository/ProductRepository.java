package com.example.nxttrendz2.repository;

import java.util.ArrayList;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;

public interface ProductRepository {

    ArrayList<Product> getProducts();
    Product addProduct(Product product);
    Product getProductById(int productId);
    Product updateProduct(int productId, Product product);
    void deleteProduct(int productId);
    Category getCategoryByProduct(int productId);

}