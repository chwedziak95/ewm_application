package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersDto{
    private Long ordersId;
    private String orderNumber;
    private Instant orderDate;
    private Instant deliveryDate;
    private String comment;
    private VendorDto vendor;
    private UserEqDto user;
    private String statusName;
    private Set<OrderItemDto> orderItems = new HashSet<>();
}