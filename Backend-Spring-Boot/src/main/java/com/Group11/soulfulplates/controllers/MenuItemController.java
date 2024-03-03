package com.Group11.soulfulplates.controllers;


import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.payload.request.MenuItemRequest;
import com.Group11.soulfulplates.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/add/{sellerId}")
    public ResponseEntity<?> createMenuItem(@PathVariable Long sellerId, @RequestBody(required = false) MenuItem menuItemRequest) {
        try {
//            System.out.println("Hello I am running");
//            System.out.println(menuItemRequest);
            MenuItem createdMenuItem = menuItemService.createMenuItem(menuItemRequest);
            return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        return menuItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItem);
        return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
    }
}
