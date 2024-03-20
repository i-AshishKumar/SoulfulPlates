package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.SubCategory;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SubcategoryServiceImplTest {

    @Mock
    private SubcategoryRepository subcategoryRepository;

    @InjectMocks
    private SubcategoryServiceImpl subcategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateSubcategory() {
        SubCategory subcategory = new SubCategory();
        subcategoryService.createSubcategory(subcategory);
        verify(subcategoryRepository, times(1)).save(subcategory);
    }

    @Test
    void testEditSubcategory() {
        SubCategory subcategory = new SubCategory();
        subcategoryService.editSubcategory(subcategory);
        verify(subcategoryRepository, times(1)).save(subcategory);
    }

    @Test
    void testDeleteSubcategory() {
        Long subcategoryId = 1L;
        subcategoryService.deleteSubcategory(subcategoryId);
        verify(subcategoryRepository, times(1)).deleteById(subcategoryId);
    }

    @Test
    void testGetSubcategoryById() {
        Long subcategoryId = 1L;
        SubCategory subcategory = new SubCategory();
        when(subcategoryRepository.findById(subcategoryId)).thenReturn(Optional.of(subcategory));
        assertEquals(subcategory, subcategoryService.getSubcategoryById(subcategoryId));
    }

    @Test
    void testGetAllSubcategories() {
        List<SubCategory> subcategories = Arrays.asList(new SubCategory(), new SubCategory());
        when(subcategoryRepository.findAll()).thenReturn(subcategories);
        assertEquals(subcategories, subcategoryService.getAllSubcategories());
    }
}
