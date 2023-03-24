package com.kc6379.zarzadzaniemagazynem.dto;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long ordersId;
    private String orderNumber;
    private String comment;
    private Set<OrderItemRequest> orderItems;

}
