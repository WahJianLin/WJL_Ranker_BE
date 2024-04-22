package com.wjl.ranker.services;

import com.wjl.ranker.DTO.CategoryDTO;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.repositories.CategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    // TODO create validator that validates the CategoriesDTOs
    // TODO use specific exceptions

    final private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return toDTO(getCategory(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepo.save(toEntity(categoryDTO));
            return toDTO(category);
        } catch (Exception e) {
            throw new RuntimeException("Category failed to Save");
        }
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = getCategory(categoryDTO.getId());
        try {
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepo.save(category);
            return categoryDTO;
        } catch (Exception e) {
            throw new RuntimeException("Category failed to update");
        }
    }

    @Override
    public void deleteCategory(long id) {
        if (!categoryRepo.existsById(id)) {
            throw new IllegalStateException("Category " + id + " does not exist");
        } else {
            categoryRepo.deleteById(id);
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

    private Category getCategory(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }
}
