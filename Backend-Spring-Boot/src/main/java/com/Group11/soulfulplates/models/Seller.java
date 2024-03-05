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

    @Column(name = "sellerName", unique = true)
    private String sellerName;

    @Column(name = "sellerEmail", unique = true)
    private String sellerEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getName() {
        return sellerName;
    }
}
