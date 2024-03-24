package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.repository.CategoryRepository;
import com.Group11.soulfulplates.repository.MenuItemRepository;
import com.Group11.soulfulplates.repository.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SubcategoryRepository subcategoryRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMenuItem() {
        MenuItem menuItem = new MenuItem();
        menuItemService.createMenuItem(menuItem);
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    public void testFindMenuById() {
        Long menuItemId = 1L;
        menuItemService.findMenuById(menuItemId);
        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    public void testEditMenuItem() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItemService.editMenuItem(menuItemId, menuItem);
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    public void testDeleteMenuItem() {
        Long menuItemId = 1L;
        menuItemService.deleteMenuItem(menuItemId);
        verify(menuItemRepository, times(1)).deleteById(menuItemId);
    }
}
