package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.models.SubCategory;
import com.Group11.soulfulplates.payload.request.MenuItemRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.CategoryRepository;
import com.Group11.soulfulplates.repository.MenuItemRepository;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public void createMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public MenuItem findMenuById(Long categoryId) {
        return menuItemRepository.findById(categoryId).orElse(null);
    }


    public void editMenuItem(Long menuItemId, MenuItem menuItem) {
        menuItemRepository.save(menuItem);

    }

    public void deleteMenuItem(Long menuItemId) {
            menuItemRepository.deleteById(menuItemId);
    }

}
