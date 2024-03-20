package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.SubCategory;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import com.Group11.soulfulplates.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public void createSubcategory(SubCategory subcategory) {
        subcategoryRepository.save(subcategory);
    }

    public void editSubcategory(SubCategory subcategory) {
        subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long subcategoryId) {
        subcategoryRepository.deleteById(subcategoryId);
    }

    public SubCategory getSubcategoryById(Long subcategoryId) {
        return subcategoryRepository.findById(subcategoryId).orElse(null);
    }

    public List<SubCategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    // Other methods as needed
}
