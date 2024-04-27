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
    // TODO use specific exceptions

    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        try {
            List<Category> response = categoryService.getAllCategories();
            return ResponseEntity.ok(response.stream().map(this::toDTO).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(Long id) {
        try {
            Category response = categoryService.getCategoryById(id);
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        try {
            Category response = categoryService.createCategory(toEntity(categoryDTO));
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(CategoryDTO categoryDTO) {
        try {
            Category response = categoryService.updateCategory(toEntity(categoryDTO));
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity deleteCategory(Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
