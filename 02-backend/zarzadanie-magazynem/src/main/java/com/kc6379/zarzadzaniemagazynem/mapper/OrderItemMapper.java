package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import org.mapstruct.*;

@Mapper( componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem toEntity(OrderItemRequest orderItemRequest);

    OrderItemDto toDto(OrderItem orderItem);
}