package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDelivery{
    private Long ordersId;
    private Instant deliveryDate;
}