package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemDto{
    private Long orderItemId;
    private MaterialResponse materialId;
    private Integer quantity;
}