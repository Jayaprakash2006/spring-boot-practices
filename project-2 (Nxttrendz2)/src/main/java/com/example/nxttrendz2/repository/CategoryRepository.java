package com.example.nxttrendz2.repository;

import java.util.ArrayList;
import com.example.nxttrendz2.model.Category;

public interface CategoryRepository {

    ArrayList<Category> getCategories();
    Category addCategory(Category category);
    Category getCategoryById(int categoryId);
    Category updateCategory(int categoryId, Category category);
    void deleteCategory(int categoryId);

}