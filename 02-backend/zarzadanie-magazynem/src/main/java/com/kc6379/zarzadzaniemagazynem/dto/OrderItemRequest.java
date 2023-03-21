package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.OrderItem} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequest{
    private Long materialId;
    private Integer quantity;
}