package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "service_provider_id")
    private Long serviceProviderId;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    // Default constructor
    public Cart() {
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", serviceProviderId=" + serviceProviderId +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
