package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "subcategories")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @Column(name = "name", nullable = false)
    private String name;

    // Define a relationship with the Category model
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

//     //Define a set of items for this subcategory
//    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
//    private Set<MenuItem> items = new HashSet<>();

}
