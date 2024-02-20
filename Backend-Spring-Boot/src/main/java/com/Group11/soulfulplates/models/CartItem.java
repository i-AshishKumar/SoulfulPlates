package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "added_date", nullable = false)
    private LocalDateTime addedDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "order_id")
    private Long orderId;

    // Default constructor
    public CartItem() {
    }

    // Getters and setters

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartId() {
        return cart.getCartId();
    }

    public void setCartId(Long cartId) {
        this.cart.setCartId(cartId);
    }

    public Long getMenuItemId() {
        return menuItem.getMenuItemId();
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItem.setMenuItemId(menuItemId);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cart.getCartId() +
                ", menuItemId=" + menuItem.getMenuItemId() +
                ", quantity=" + quantity +
                ", addedDate=" + addedDate +
                ", notes='" + notes + '\'' +
                ", orderId=" + orderId +
                '}';
    }

}
