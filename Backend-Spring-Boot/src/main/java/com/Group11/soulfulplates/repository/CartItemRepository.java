package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CartItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Find cart items by cart ID
    public List<CartItem> findByCartId(Long cartId) {
        return entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.cartId = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }

    // Find cart items by cart and menu item ID
    public Optional<CartItem> findByCartIdAndMenuItemId(Long cartId, Long menuItemId) {
        try {
            CartItem cartItem = entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.cartId = :cartId AND ci.menuItemId = :menuItemId", CartItem.class)
                    .setParameter("cartId", cartId)
                    .setParameter("menuItemId", menuItemId)
                    .getSingleResult();
            return Optional.of(cartItem);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    // Method to find a cart item by ID
    public Optional<CartItem> findById(Long cartItemId) {
        CartItem cartItem = entityManager.find(CartItem.class, cartItemId);
        return Optional.ofNullable(cartItem);
    }
    }
}
