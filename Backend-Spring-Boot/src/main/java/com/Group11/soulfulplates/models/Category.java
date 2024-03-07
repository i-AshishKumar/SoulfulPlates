package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private CategoryEnum name;

//    // Define the many-to-one relationship with the Seller entity
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id", nullable = false)
//    private Store store;
//
//    // Define a set of subcategories for this category
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private Set<SubCategory> subCategories = new HashSet<>();

}
