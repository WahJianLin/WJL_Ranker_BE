package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.CategoryDTO;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> response = categoryService.getAll();
        return ResponseEntity.ok(response.stream().map(this::toDTO).toList());
    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(Long id) {
        Category response = categoryService.getById(id);
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        Category response = categoryService.create(toEntity(categoryDTO));
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(CategoryDTO categoryDTO) {
        Category response = categoryService.update(toEntity(categoryDTO));
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    private Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
