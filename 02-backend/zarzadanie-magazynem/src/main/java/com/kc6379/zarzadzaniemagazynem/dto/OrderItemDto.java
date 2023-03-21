package com.kc6379.zarzadzaniemagazynem.dto;

import com.kc6379.zarzadzaniemagazynem.model.OrderItem;
import lombok.*;

/**
 * A DTO for the {@link OrderItem} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemDto{
    private Long orderItemId;
    private MaterialResponse materialId;
    private Integer quantity;
}