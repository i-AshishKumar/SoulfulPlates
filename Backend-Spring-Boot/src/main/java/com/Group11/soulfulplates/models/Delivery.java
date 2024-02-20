package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private int order;

    @Column(name = "expected_delivery_time", nullable = false)
    private LocalDateTime expectedDeliveryTime;

    @Column(name = "delivery_start_time")
    private LocalDateTime deliveryStartTime;

    @Column(name = "delivery_end_time")
    private LocalDateTime deliveryEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false)
    private EDelivery currentStatus;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id", nullable = false)
    private DeliveryPerson deliveryPerson;




}
