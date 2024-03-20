package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.SubCategory;
import com.Group11.soulfulplates.payload.request.CategoryRequest;
import com.Group11.soulfulplates.payload.request.SubcategoryRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.CategoryService;
import com.Group11.soulfulplates.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    // Create category
    @PostMapping("/category")
    public MessageResponse createCategory(@RequestBody CategoryRequest request) {
        Category category = new Category(request.getCategoryName(), request.getStoreId());
        categoryService.createCategory(category);
        return new MessageResponse(1, "Category Created.",null);
    }

    // Edit category
    @PutMapping("/category/{categoryId}")
    public MessageResponse editCategory(@RequestBody CategoryRequest request, @PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        category.setCategoryName(request.getCategoryName());
        categoryService.editCategory(category);
        return new MessageResponse(1, "Category updated.",null);

    }

    // Delete category
    @DeleteMapping("/category/{categoryId}")
    public MessageResponse deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new MessageResponse(1, "Category deleted.",null);
    }

    // Create subcategory
    @PostMapping("/subcategory")
    public MessageResponse createSubcategory(@RequestBody SubcategoryRequest request) {
        Category category = categoryService.getCategoryById(request.getCategoryId());
        SubCategory subcategory = new SubCategory(request.getSubcategoryName(), category, request.getStoreId());
        subcategoryService.createSubcategory(subcategory);
        return new MessageResponse(1, "Subcategory Created.",null);
    }

    // Edit subcategory
    @PutMapping("/subcategory/{subcategoryId}")
    public MessageResponse editSubcategory(@RequestBody SubcategoryRequest request, @PathVariable Long subcategoryId) {
        SubCategory subcategory = subcategoryService.getSubcategoryById(subcategoryId);
        subcategory.setSubcategoryName(request.getSubcategoryName());
        subcategoryService.editSubcategory(subcategory);
        return new MessageResponse(1, "Subcategory updated.",null);

    }

    // Delete subcategory
    @DeleteMapping("/subcategory/{subcategoryId}")
    public MessageResponse deleteSubcategory(@PathVariable Long subcategoryId) {
        subcategoryService.deleteSubcategory(subcategoryId);
        return new MessageResponse(1, "Subcategory deleted.",null);

    }

    @GetMapping("/categories/{storeId}")
    public MessageResponse getAllCategoriesWithSubcategoriesByStoreId(@PathVariable Long storeId) {
         List<Category> categories =         categoryService.findAllCategoriesWithSubcategoriesByStoreId(storeId);

        return new MessageResponse(1,  "Categories with Subcategories", categories);

    }
}