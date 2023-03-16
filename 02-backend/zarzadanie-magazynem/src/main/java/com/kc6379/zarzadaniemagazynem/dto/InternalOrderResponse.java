package com.kc6379.zarzadaniemagazynem.dto;

import com.kc6379.zarzadaniemagazynem.model.Status;
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

    private Long id;
    private UserEqDto user;
    private LocalDateTime orderDateTIme;
    private LocalDateTime pickupDateTime;
    private Status status;
    private String pickLocation;
    private Set<OrderItemDto> orderItems;

}
