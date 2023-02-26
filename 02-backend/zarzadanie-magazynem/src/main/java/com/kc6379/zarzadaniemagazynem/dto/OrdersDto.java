package com.kc6379.zarzadaniemagazynem.dto;

import com.kc6379.zarzadaniemagazynem.model.Status;
import com.kc6379.zarzadaniemagazynem.model.User;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private Long ordersId;
    private String orderNumber;
    private Instant orderDate;
    private Date deliveryDate;
    private String comment;
    private VendorDto vendor;
    private UserEqDto user;
    private Status status;
    private Set<OrderItemDto> orderItems;
}
