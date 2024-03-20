package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Rating;
import com.Group11.soulfulplates.payload.request.CreateRatingRequest;
import com.Group11.soulfulplates.repository.OrderRepository;
import com.Group11.soulfulplates.repository.RatingRepository;
import com.Group11.soulfulplates.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAverageRating() {
        // Mocking data
        Rating rating1 = new Rating();
        rating1.setRating((int) 4.0);
        Rating rating2 = new Rating();
        rating2.setRating((int) 3.0);

        when(ratingRepository.findByStoreStoreId(1L)).thenReturn(Arrays.asList(rating1, rating2));

        // Test
        double averageRating = ratingService.getAverageRating(1L);
        assertEquals(3.5, averageRating);
    }

    @Test
    void testGetAverageRating_EmptyRatings() {
        when(ratingRepository.findByStoreStoreId(1L)).thenReturn(Arrays.asList());

        // Test
        double averageRating = ratingService.getAverageRating(1L);
        assertEquals(0, averageRating);
    }

    @Test
    void testAddRatingAndLinkToOrder_InvalidOrder() {
        // Mocking data with null order ID
        CreateRatingRequest ratingRequest = new CreateRatingRequest();
        ratingRequest.setStoreId(1L); // Providing store ID
        ratingRequest.setRating((int) 4.5);
        ratingRequest.setFeedback("Good service");

        // Test
        Exception exception = assertThrows(Exception.class, () -> ratingService.addRatingAndLinkToOrder(ratingRequest));
        assertEquals("Order is Null in request", exception.getMessage());
    }

}
