package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Orders} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDelivery{
    private Long ordersId;
    private Instant deliveryDate;
}