package com.kc6379.zarzadaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.OrderItem} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemRequest implements Serializable {
    private Long materialId;
    private Integer quantity;
}