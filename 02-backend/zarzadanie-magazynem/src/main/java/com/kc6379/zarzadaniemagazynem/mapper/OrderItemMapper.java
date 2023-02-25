package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem toEntity(OrderItemRequest orderItemRequest);

    @Mapping(source = "materialId.materialId", target = "materialId")
    OrderItemRequest toDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem partialUpdate(OrderItemRequest orderItemRequest, @MappingTarget OrderItem orderItem);
}