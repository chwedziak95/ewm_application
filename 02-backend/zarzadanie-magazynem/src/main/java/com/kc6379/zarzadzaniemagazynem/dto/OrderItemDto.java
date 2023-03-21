package com.kc6379.zarzadaniemagazynem.dto;

import com.kc6379.zarzadaniemagazynem.dto.MaterialResponse;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import lombok.*;

import java.io.Serializable;

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