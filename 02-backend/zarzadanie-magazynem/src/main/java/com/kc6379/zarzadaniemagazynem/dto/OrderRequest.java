package com.kc6379.zarzadaniemagazynem.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long ordersId;
    private String orderNumber;
    private String comment;
    private Set<OrderItemRequest> orderItems;

}
