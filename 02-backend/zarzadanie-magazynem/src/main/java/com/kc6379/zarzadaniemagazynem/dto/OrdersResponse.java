package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersResponse {
    private Long ordersId;
    private String orderNumber;
    private Double orderTotal;
    private Instant orderDate;
    private Instant deliveryDate;
    private String comment;
    private UserEqDto user;
    private StatusDto status;
    private Set<OrderItemDto> orderItems;
}