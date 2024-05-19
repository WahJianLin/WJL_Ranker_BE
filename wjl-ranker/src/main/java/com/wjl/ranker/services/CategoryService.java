package com.wjl.ranker.services;

import com.wjl.ranker.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category create(Category categoryEntity);

    Category update(Category categoryEntity);

    void deleteById(long id);
}
