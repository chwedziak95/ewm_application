package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersResponse implements Serializable {
    private Long ordersId;
    private String orderNumber;
    private Instant orderDate;
    private Date deliveryDate;
    private String comment;
    private UserEqDto user;
    private StatusDto status;
    private Set<OrderItemDto> orderItems = new HashSet<>();
}