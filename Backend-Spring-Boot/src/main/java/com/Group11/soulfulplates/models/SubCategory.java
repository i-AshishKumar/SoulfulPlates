package com.Group11.soulfulplates.models;
import jakarta.persistence.*;

@Entity
@Table(name = "subcategories")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @Column(name = "subcategory_name")
    private String subcategoryName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "store_id")
    private Long storeId;

    // Constructors
    public SubCategory() {}

    public SubCategory(String subcategoryName, Category category, Long storeId) {
        this.subcategoryName = subcategoryName;
        this.category = category;
        this.storeId = storeId;
    }

    // Getters and setters
    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
