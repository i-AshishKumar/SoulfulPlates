package com.Group11.soulfulplates.payload.request;


import com.Group11.soulfulplates.models.EMenuItem;
import com.Group11.soulfulplates.models.Seller;
import jakarta.persistence.*;

public class MenuItemRequest {

    private Long menuItemId;

    private Long seller;

    private String name;

    private String description;

    private String image;

    private int price;

    private String type;

    private boolean isActive;

    private String category;

    // Constructors
    public MenuItemRequest() {}

    public MenuItemRequest(Long menuItemId, Long seller,String name,String description,String image, int price,String type, boolean isActive, String category) {
        this.menuItemId = menuItemId;
        this.seller = seller;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.type = type;
        this.isActive = isActive;
        this.category = category;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
