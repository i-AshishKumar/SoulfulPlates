package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "sellerName", nullable = false)
    private String sellerName;

    @Column(name = "sellerEmail", nullable = false, unique = true)
    private String sellerEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // This establishes the relationship with the User entity

    public String getName() {
        return sellerName;
    }
}
