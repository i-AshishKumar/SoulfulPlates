package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.services.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        System.out.println(">>>>>1");
        MenuItem createdMenuItem = menuItemService.saveOrUpdate(menuItem);
        return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.findAll();
        return ResponseEntity.ok("menuItems");
    }



}