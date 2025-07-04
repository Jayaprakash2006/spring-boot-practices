package com.example.nxttrendz2.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.service.CategoryJpaService;

@RestController
public class CategoryController {

    @Autowired
    CategoryJpaService service;

    @GetMapping("/categories")
    public ArrayList<Category> getCategories() {
        return service.getCategories();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") int categoryId) {
        return service.getCategoryById(categoryId);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        return service.updateCategory(categoryId, category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        service.deleteCategory(categoryId);
    }
}