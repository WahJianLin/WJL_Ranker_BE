package com.wjl.ranker.services;

import com.wjl.ranker.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);
    CategoryEntity createCategory(CategoryEntity category);
    CategoryEntity updateCategory(CategoryEntity category);
    void deleteCategory(long id);
}
