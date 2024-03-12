package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Sub-Category_Table")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subcategoryId;

    private String subcategoryName;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    // Getters and setters
}
