package com.kc6379.zarzadzaniemagazynem.dto;

import com.kc6379.zarzadzaniemagazynem.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
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