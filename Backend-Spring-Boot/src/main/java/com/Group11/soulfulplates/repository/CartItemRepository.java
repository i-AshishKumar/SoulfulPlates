package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CartItem> findByUserIdAndIsActive(Long userId, Boolean isActive) {
        return entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.userId = :userId AND ci.isActive = :isActive", CartItem.class)
                .setParameter("userId", userId)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<CartItem> findCartItemsByUserId(Long userId) {
        return entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.userId = :userId", CartItem.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
