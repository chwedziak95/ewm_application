package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalOrderRequest {
    private Long internalOrderId;
    private String pickUpLocation;
    private Set<OrderItemRequest> orderItems;
}
