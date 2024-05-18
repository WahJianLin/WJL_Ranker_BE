package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.exceptions.GeneralException;
import com.wjl.ranker.repositories.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    public static final String ENTITY = "CATEGORY";

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
        return categoryRepo.findById(id).orElseThrow(() -> {
            log.error(String.format(Constants.LOG_FAILED_FIND, ENTITY, id));
            return new GeneralException(String.format(Constants.EXCEPTION_GENERAL_NOT_FOUND, ENTITY));
        });
    }

    @Override
    public Category createCategory(Category categoryEntity) {
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_SAVE, ENTITY, categoryEntity.getId(), categoryEntity)));
            return categoryRepo.save(categoryEntity);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_SAVE, ENTITY, categoryEntity.getId(), categoryEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_SAVE, ENTITY));
        }
    }

    @Override
    public Category updateCategory(Category categoryEntity) {
        Category category = getCategoryById(categoryEntity.getId());
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_UPDATE, ENTITY, categoryEntity.getId(), categoryEntity)));

            category.setName(categoryEntity.getName());
            category.setDescription(categoryEntity.getDescription());
            return categoryRepo.save(category);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_UPDATE, ENTITY, categoryEntity.getId(), categoryEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_UPDATE, ENTITY));
        }
    }

    @Override
    public void deleteCategory(long id) {
        log.info(String.format(Constants.LOG_ATTEMPTING_TO_DELETE, ENTITY, id));
        if (!categoryRepo.existsById(id)) {
            log.error(String.format(Constants.LOG_FAILED_DELETE, ENTITY, id));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY));
        }
        categoryRepo.deleteById(id);
        log.info(String.format(Constants.LOG_SUCCESSFUL_DELETE, ENTITY, id));
    }
}
