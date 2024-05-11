package com.wjl.ranker.services;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.exception.GeneralException;
import com.wjl.ranker.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    // TODO create validator that validates the CategoriesDTOs

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new GeneralException("Category not found"));
    }

    @Override
    public Category createCategory(Category categoryEntity) {
        try {
            return categoryRepo.save(categoryEntity);
        } catch (Exception e) {
            throw new GeneralException("Category failed to Save");
        }
    }

    @Override
    public Category updateCategory(Category categoryEntity) {
        Category category = getCategoryById(categoryEntity.getId());
        try {
            category.setName(categoryEntity.getName());
            category.setDescription(categoryEntity.getDescription());
            return categoryRepo.save(category);
        } catch (Exception e) {
            throw new GeneralException("Category failed to update");
        }
    }

    @Override
    public void deleteCategory(long id) {
        if (!categoryRepo.existsById(id)) {
            throw new GeneralException("Category " + id + " does not exist");
        } else {
            categoryRepo.deleteById(id);
        }
    }
}
