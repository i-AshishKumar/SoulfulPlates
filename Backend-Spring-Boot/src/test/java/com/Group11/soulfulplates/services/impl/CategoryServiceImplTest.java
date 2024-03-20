package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCategoryName("Test Category");

        categoryService.createCategory(category);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testEditCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Test Category");

        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.editCategory(category);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category retrievedCategory = categoryService.getCategoryById(categoryId);

        assertNotNull(retrievedCategory);
        assertEquals(categoryId, retrievedCategory.getCategoryId());
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> retrievedCategories = categoryService.getAllCategories();

        assertNotNull(retrievedCategories);
        assertEquals(categories.size(), retrievedCategories.size());
    }

    @Test
    void testFindAllCategoriesWithSubcategoriesByStoreId() {
        Long storeId = 1L;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAllCategoriesWithSubcategoriesByStoreId(storeId)).thenReturn(categories);

        List<Category> retrievedCategories = categoryService.findAllCategoriesWithSubcategoriesByStoreId(storeId);

        assertNotNull(retrievedCategories);
        assertEquals(categories.size(), retrievedCategories.size());
    }
}
