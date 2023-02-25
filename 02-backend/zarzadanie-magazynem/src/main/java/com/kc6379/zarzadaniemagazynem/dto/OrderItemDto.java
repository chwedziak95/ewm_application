package com.kc6379.zarzadaniemagazynem.dto;

import com.kc6379.zarzadaniemagazynem.dto.MaterialResponse;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link OrderItem} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto implements Serializable {
    private MaterialResponse materialId;
    private Integer quantity;
}