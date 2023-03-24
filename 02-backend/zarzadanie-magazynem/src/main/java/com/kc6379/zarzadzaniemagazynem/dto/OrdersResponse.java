package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrdersResponse {
    private Long ordersId;
    private String orderNumber;
    private Double orderTotal;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String comment;
    private UserDto user;
    private StatusDto status;
    private Set<OrderItemDto> orderItems;
}