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

    @Column(name = "order_id") // Consider renaming to avoid naming conflict
    private Long orderId;


}
