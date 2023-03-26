package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadzaniemagazynem.model.InternalOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalOrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "materialId", target ="materialId.materialId")
    InternalOrderItem toEntity(OrderItemRequest orderItemRequest);

    @Mapping(target = "orderItemId", ignore = true)
    OrderItemDto toDto(InternalOrderItem internalOrderItem);
}