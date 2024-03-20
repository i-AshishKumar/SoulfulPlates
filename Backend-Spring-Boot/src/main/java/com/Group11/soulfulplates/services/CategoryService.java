package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Category;
import java.util.List;

public interface CategoryService {
    void createCategory(Category category);
    void editCategory(Category category);
    void deleteCategory(Long categoryId);
    Category getCategoryById(Long categoryId);
    List<Category> getAllCategories();
    List<Category> findAllCategoriesWithSubcategoriesByStoreId(Long storeId);
}
