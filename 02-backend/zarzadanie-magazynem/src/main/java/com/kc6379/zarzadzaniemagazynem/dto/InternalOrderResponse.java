package com.kc6379.zarzadzaniemagazynem.dto;

import com.kc6379.zarzadzaniemagazynem.model.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InternalOrderResponse {

    private Long internalOrderId;
    private UserDto user;
    private LocalDateTime orderDate;
    private LocalDateTime pickDate;
    private Status status;
    private String pickUpLocation;
    private Set<OrderItemDto> orderItems;

}