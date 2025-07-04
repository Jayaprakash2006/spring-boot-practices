package com.example.nxttrendz2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.repository.CategoryRepository;
import com.example.nxttrendz2.repository.CategoryJpaRepository;

@Service
public class CategoryJpaService implements CategoryRepository {

    @Autowired
    CategoryJpaRepository db;

    public ArrayList<Category> getCategories() {
        return (ArrayList<Category>) db.findAll();
    }

    public Category addCategory(Category category) {
        return db.save(category);
    }

    public Category getCategoryById(int categoryId) {
        try {
            Category category = db.findById(categoryId).get();
            return category;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Category updateCategory(int categoryId, Category category) {
        try {
            Category ext = db.findById(categoryId).get();
            if(category.getName() != null) ext.setName(category.getName());
            if(category.getDescription() != null) ext.setDescription(category.getDescription());

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCategory(int categoryId) {
        try {
            db.deleteById(categoryId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}