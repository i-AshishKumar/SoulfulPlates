package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.repository.MenuItemRepository;
import com.Group11.soulfulplates.services.MenuItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem addMenuItem(MenuItem menuItem) {
        // Additional logic if needed
        return menuItemRepository.save(menuItem);
    }
}
