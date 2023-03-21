package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadaniemagazynem.model.InternalOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalOrderItemMapper {
    @Mapping(source = "materialId", target ="materialId.materialId")
    InternalOrderItem toEntity(OrderItemRequest orderItemRequest);

    OrderItemDto toDto(InternalOrderItem internalOrderItem);
}
