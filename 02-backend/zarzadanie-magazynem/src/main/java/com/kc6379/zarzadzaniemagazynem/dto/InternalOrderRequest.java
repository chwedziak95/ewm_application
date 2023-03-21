package com.kc6379.zarzadaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalOrderRequest {
    private Long internalOrderId;
    private String pickUpLocation;
    private Set<OrderItemRequest> orderItems;
}
