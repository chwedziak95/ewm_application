package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.OrderItem} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequest{
    private Long materialId;
    private Integer quantity;
}