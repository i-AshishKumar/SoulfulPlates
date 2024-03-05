package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "seller")

public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id", unique = true)
    private Long sellerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private SellerAddress sellerAddress;

    public Seller() {
        sellerAddress = new SellerAddress();
    }

    public Seller(SellerAddress SellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    // Getters and setters
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public Long getSellerAddressId() {
        return sellerAddress.getLocationId();
    }

    public void setSellerAddressId(Long sellerAddressId) {
        this.sellerAddress.setLocationId(sellerAddressId);
    }
}
