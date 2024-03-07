package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CATEGORY_TABLE")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private Store seller;

    private String categoryName;

    // Getters and setters
}
