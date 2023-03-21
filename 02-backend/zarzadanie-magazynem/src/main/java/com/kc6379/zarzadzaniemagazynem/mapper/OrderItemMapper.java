package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadzaniemagazynem.model.OrderItem;
import org.mapstruct.*;

@Mapper( componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem toEntity(OrderItemRequest orderItemRequest);

    OrderItemDto toDto(OrderItem orderItem);
}