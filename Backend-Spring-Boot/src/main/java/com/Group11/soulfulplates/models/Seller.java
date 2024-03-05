package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id", unique = true)
    private Long sellerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "seller_email", unique = true)
    private String sellerEmail;

    @Column(name = "seller_name", unique = true)
    private String sellerName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private SellerAddress sellerAddress;

    public Seller() {
        sellerAddress = new SellerAddress();
    }

    public Seller(SellerAddress sellerAddress) {
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
        return sellerName;
    }

    public void setName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getEmail() {
        return sellerEmail;
    }

    public void setEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
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
