package com.kc6379.zarzadzaniemagazynem.dto;

import com.kc6379.zarzadzaniemagazynem.model.Status;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersResponse {
    private Long ordersId;
    private String orderNumber;
    private Double orderTotal;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String comment;
    private UserDto user;
    private Status status;
    private Set<OrderItemDto> orderItems;
}