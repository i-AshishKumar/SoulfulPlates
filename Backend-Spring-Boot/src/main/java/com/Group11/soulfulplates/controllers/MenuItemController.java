package com.Group11.soulfulplates.controllers;


import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.payload.request.MenuItemRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public MessageResponse createMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        mapRequestToMenuItem(menuItemRequest, menuItem);
        menuItemService.createMenuItem(menuItem);
        return new MessageResponse(1, "Menu item created.", null);
    }

    @PutMapping("/{menuItemId}")
    public MessageResponse editMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemRequest menuItemRequest) {

        MenuItem menuItem = menuItemService.findMenuById(menuItemId);

        if (menuItem != null) {
            mapRequestToMenuItem(menuItemRequest, menuItem);
            menuItemService.editMenuItem(menuItemId, menuItem);
            return new MessageResponse(1, "Menu item updated successfully.", null);
        } else {
            return new MessageResponse(0, "Menu item not found.", null);
        }
    }

    @DeleteMapping("/{menuItemId}")
    public MessageResponse deleteMenuItem(@PathVariable Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return new MessageResponse(1, "Menu item deleted successfully.", null);
    }


    private void mapRequestToMenuItem(MenuItemRequest menuItemRequest, MenuItem menuItem) {
        menuItem.setStoreId(menuItemRequest.getStoreId());
        menuItem.setItemName(menuItemRequest.getItemName());
        menuItem.setItemImage(menuItemRequest.getItemImage());
        menuItem.setItemPrice(menuItemRequest.getItemPrice());
        menuItem.setType(menuItemRequest.getType());
        menuItem.setCategoryId(menuItemRequest.getCategoryId());
        menuItem.setSubCategoryId(menuItemRequest.getSubCategoryId());
        menuItem.setServingType(menuItemRequest.getServingType());
        menuItem.setPortion(menuItemRequest.getPortion());
        menuItem.setInStock(menuItemRequest.getInStock());
        menuItem.setIsRecommended(menuItemRequest.getIsRecommended());
        menuItem.setDescription(menuItemRequest.getDescription());
    }
}
