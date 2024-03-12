package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAllItems();
    MenuItem getItemById(Long id);
    MenuItem createItem(MenuItem item);
    MenuItem updateItem(Long id, MenuItem item);
    void deleteItem(Long id);
}
