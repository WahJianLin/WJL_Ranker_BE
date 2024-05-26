package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.CategoryDTO;
import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface CategoryController {
    @GetMapping()
    ResponseEntity<List<CategoryDTO>> getAllCategories();

    @GetMapping("{id}")
    ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO);

    @PutMapping()
    ResponseEntity<CategoryDTO> updateCategory(@Validated(OnUpdateValidation.class) @RequestBody CategoryDTO categoryDTO);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);
}
