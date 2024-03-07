package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    MenuItem saveOrUpdate(MenuItem menuItem);
    MenuItem findById(Long id);
    List<MenuItem> findAll();
    void delete(Long id);
}