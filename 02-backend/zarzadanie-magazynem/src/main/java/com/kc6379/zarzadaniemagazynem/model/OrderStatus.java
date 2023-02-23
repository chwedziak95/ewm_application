package com.kc6379.zarzadaniemagazynem.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderStatusId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "ordersId",
            referencedColumnName = "ordersId"
    )
    private Orders ordersId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "statusId",
            referencedColumnName = "statusId"
    )
    private Status statusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User userId;

    private Instant createdDate;
}
