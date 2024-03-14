package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public void editCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllCategoriesWithSubcategoriesByStoreId(Long storeId){
        return categoryRepository.findAllCategoriesWithSubcategoriesByStoreId(storeId);
    }

}
