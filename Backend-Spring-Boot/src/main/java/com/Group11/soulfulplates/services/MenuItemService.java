package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.MenuItem;

public interface MenuItemService {
    void createMenuItem(MenuItem menuItem);
    MenuItem findMenuById(Long menuItemId);
    void editMenuItem(Long menuItemId, MenuItem menuItem);
    void deleteMenuItem(Long menuItemId);
}
