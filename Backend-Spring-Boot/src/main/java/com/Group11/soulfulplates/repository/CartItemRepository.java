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

    }
}
