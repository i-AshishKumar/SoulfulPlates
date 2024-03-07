package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    private Store store;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EMenuItem type;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "category")
    private String category;

    // Constructor
    public MenuItem(){
        this.seller = new Seller();

    }

    // Getters and setters
    public Long getMenuItemId() {
        return menuItemId;
    }

    public Long setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
        return menuItemId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
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

    public EMenuItem getType() {
        return type;
    }

    public void setType(EMenuItem type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

/*
* {
    "menuItemId": 1,
    "seller": 1,
    "name": "sambar idli",
    "description": "spicy tangy side with steamed rice cakes",
    "image": "idlisambar.png",
    "price": 50,
    "type": "ONCE",
    "isActive": true,
    "category": "breakfast"
}
*
* */