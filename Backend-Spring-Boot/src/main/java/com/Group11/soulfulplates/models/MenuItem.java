package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id",nullable = false)
    private Long menuItemId;

    @Column(name = "name", nullable = false)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String itemImage;

    @Column(name = "price", nullable = false)
    private int itemPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EMenuItem type;

    @Column(name = "serving_type")
    private int servingType;

    @Column(name = "portion")
    private String portion;

    @Column(name = "in_stock", nullable = false)
    private boolean inStock;

    @Column(name = "is_recommended", nullable = false)
    private boolean isRecommended;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subcategory_id", nullable = false)
//    private SubCategory subCategory;

}