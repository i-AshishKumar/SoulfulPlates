package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByOrderOrderId(long orderId);
}
