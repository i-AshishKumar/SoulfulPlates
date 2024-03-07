package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.services.MenuItemService;
import com.Group11.soulfulplates.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/menuItems")
public class MenuItemController {

    private StoreService storeService;

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        System.out.println(menuItem.toString());
//        System.out.println("Hello");
        MenuItem addedMenuItem = menuItemService.addMenuItem(menuItem);
        return new ResponseEntity<>(addedMenuItem, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Success");
    }
}
