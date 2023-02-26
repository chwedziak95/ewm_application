package com.kc6379.zarzadaniemagazynem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;
    private String orderNumber;
    private Instant orderDate;
    private Instant deliveryDate;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "statusId",
            referencedColumnName = "statusId"
    )
    private Status status;
    @OneToMany(
            mappedBy = "orders"
    )
    private Set<OrderItem> orderItems = new HashSet<>();

    public void add(OrderItem orderItem) {
        if (orderItem != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(orderItem);
            orderItem.setOrders(this);
        }
    }
}
