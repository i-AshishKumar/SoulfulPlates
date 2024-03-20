package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.SubCategory;
import java.util.List;

public interface SubcategoryService {

    void createSubcategory(SubCategory subcategory);

    void editSubcategory(SubCategory subcategory);

    void deleteSubcategory(Long subcategoryId);

    SubCategory getSubcategoryById(Long subcategoryId);

    List<SubCategory> getAllSubcategories();

    // Other methods as needed
}
