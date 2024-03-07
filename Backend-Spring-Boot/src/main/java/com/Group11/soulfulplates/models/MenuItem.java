package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    // Define the many-to-one relationship with the Seller entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "name", nullable = false)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String itemImage;

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EMenuItem type;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "in_stock", nullable = false)
    private boolean inStock;

    // Define the many-to-one relationship with Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Define the many-to-one relationship with SubCategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubCategory subCategory;

}
