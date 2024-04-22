package com.wjl.ranker.services;

import com.wjl.ranker.DTO.CategoryDTO;
import com.wjl.ranker.entities.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
