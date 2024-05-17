package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.exception.GeneralException;
import com.wjl.ranker.repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {

    public static final String ENTITY = "CATEGORY";
    Category novel;
    Category movie;
    @Spy
    private CategoryRepo categoryRepo;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        novel = new Category(
                "novel",
                "novel desc"
        );
        movie = new Category(
                "movie",
                "movie desc"
        );
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = Arrays.asList(novel, movie);
        when(categoryRepo.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        verify(categoryRepo).findAll();
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
    }

    @Test
    void testGetCategoryById() {
        long id = 1L;
        Category category = novel;
        category.setId(id);
        when(categoryRepo.findById(id)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(id);

        assertNotNull(result);
        assertEquals(id, result.getId().longValue());
    }

    @Test
    void testCreateCategory() {
        when(categoryRepo.save(novel)).thenReturn(novel);

        Category result = categoryService.createCategory(novel);

        assertNotNull(result);
        assertEquals("novel", result.getName());
        verify(categoryRepo, times(1)).save(novel);
    }

    @Test
    void testUpdateCategory() {
        long id = 1L;
        Category existingCategory = novel;
        existingCategory.setId(id);
        Category updatedCategory = movie;
        updatedCategory.setId(id);

        when(categoryRepo.findById(id)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.save(existingCategory)).thenReturn(existingCategory);

        Category result = categoryService.updateCategory(updatedCategory);

        assertNotNull(result);
        assertEquals(movie.getName(), result.getName());
    }

    @Test
    void testDeleteCategory() {
        long id = 1L;
        when(categoryRepo.existsById(id)).thenReturn(true);

        categoryService.deleteCategory(id);

        verify(categoryRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCategory_NotFound() {
        long id = 1L;
        when(categoryRepo.existsById(id)).thenReturn(false);

        try {
            categoryService.deleteCategory(id);
        } catch (GeneralException e) {
            assertEquals("Error: " + String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY), e.getMessage());
        }
    }
}
