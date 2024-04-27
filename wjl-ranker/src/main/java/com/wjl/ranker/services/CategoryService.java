package com.wjl.ranker.services;

import com.wjl.ranker.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category categoryEntity);

    Category updateCategory(Category categoryEntity);

    void deleteCategory(long id);
}
