package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long sellerId;

    @Column(name = "sellerName", unique = true)
    private String sellerName;

    @Column(name = "sellerEmail", unique = true)
    private String sellerEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

}