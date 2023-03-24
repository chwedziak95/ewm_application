package com.kc6379.zarzadzaniemagazynem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalOrderId;

    private LocalDateTime orderDate;

    private LocalDateTime pickDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "statusId",
            referencedColumnName = "statusId"
    )
    private Status status;

    private String pickUpLocation;
    @OneToMany(
            mappedBy = "internalOrder",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<InternalOrderItem> orderItems = new HashSet<>();
}