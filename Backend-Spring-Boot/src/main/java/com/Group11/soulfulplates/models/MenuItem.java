package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "item_price")
    private String itemPrice;

    @Column(name = "type")
    private String type;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(name = "serving_type")
    private Integer servingType;

    @Column(name = "portion")
    private String portion;

    @Column(name = "in_stock")
    private Boolean inStock;

    @Column(name = "is_recommended")
    private Boolean isRecommended;

    @Column(name = "description")
    private String description;

}
