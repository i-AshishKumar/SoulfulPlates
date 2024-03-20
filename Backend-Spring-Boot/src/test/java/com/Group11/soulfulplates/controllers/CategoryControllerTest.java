package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.SubCategory;
import com.Group11.soulfulplates.payload.request.CategoryRequest;
import com.Group11.soulfulplates.payload.request.SubcategoryRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.CategoryService;
import com.Group11.soulfulplates.services.SubcategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private SubcategoryService subcategoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        // Mock data
        CategoryRequest request = new CategoryRequest();
        request.setCategoryName("Test Category");
        request.setStoreId(1L);

        // Mock service method
        doNothing().when(categoryService).createCategory(any(Category.class));

        // Call controller method
        MessageResponse response = categoryController.createCategory(request);

        // Assertions
        assertEquals(1, response.getCode());
        assertEquals("Category Created.", response.getDescription());
        assertEquals(null, response.getData());
    }

    @Test
    void testGetAllCategoriesWithSubcategoriesByStoreId() {
        // Mock data
        Long storeId = 1L;
        Category category = new Category("Test Category", storeId);
        SubCategory subCategory = new SubCategory("Test Subcategory", category, storeId);
        category.setSubcategories(Collections.singletonList(subCategory));

        // Mock service method
        when(categoryService.findAllCategoriesWithSubcategoriesByStoreId(storeId)).thenReturn(Collections.singletonList(category));

        // Call controller method
        MessageResponse response = categoryController.getAllCategoriesWithSubcategoriesByStoreId(storeId);

        // Assertions
        assertEquals(1, response.getCode());
        assertEquals("Categories with Subcategories", response.getDescription());
        assertEquals(Collections.singletonList(category), response.getData());
    }

    @Test
    void testEditCategory() {
        // Mock data
        Long categoryId = 1L;
        CategoryRequest request = new CategoryRequest();
        request.setCategoryName("Updated Test Category");

        // Mock service method
        when(categoryService.getCategoryById(categoryId)).thenReturn(new Category());

        // Call controller method
        MessageResponse response = categoryController.editCategory(request, categoryId);

        // Assertions
        assertEquals(1, response.getCode());
        assertEquals("Category updated.", response.getDescription());
        assertNull(response.getData());

        // Verify service method invocation
        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

    @Test
    void testDeleteCategory() {
        // Mock data
        Long categoryId = 1L;

        // Mock service method
        doNothing().when(categoryService).deleteCategory(categoryId);

        // Call controller method
        MessageResponse response = categoryController.deleteCategory(categoryId);

        // Assertions
        assertEquals(1, response.getCode());
        assertEquals("Category deleted.", response.getDescription());
        assertNull(response.getData());

        // Verify service method invocation
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    void testCreateSubcategory() {
        // Mock data
        Long categoryId = 1L;
        Long storeId = 1L;
        SubcategoryRequest request = new SubcategoryRequest();
        request.setSubcategoryName("Test Subcategory");
        request.setCategoryId(categoryId);
        request.setStoreId(storeId);

        // Mock service method
        when(categoryService.getCategoryById(categoryId)).thenReturn(new Category());
        doNothing().when(subcategoryService).createSubcategory(any(SubCategory.class));

        // Call controller method
        MessageResponse response = categoryController.createSubcategory(request);

        // Assertions
        assertEquals(1, response.getCode());
        assertEquals("Subcategory Created.", response.getDescription());
        assertNull(response.getData());

        // Verify service method invocation
        verify(categoryService, times(1)).getCategoryById(categoryId);
        verify(subcategoryService, times(1)).createSubcategory(any(SubCategory.class));
    }

}
