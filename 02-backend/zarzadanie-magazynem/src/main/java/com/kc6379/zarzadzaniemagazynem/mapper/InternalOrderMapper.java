package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.*;
import com.kc6379.zarzadzaniemagazynem.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(unmappedTargetPolicy = IGNORE,componentModel = "spring")
public interface InternalOrderMapper {

    InternalOrderMapper INSTANCE = Mappers.getMapper(InternalOrderMapper.class);
    @Mapping(source = "user", target = "user.userId")
    @Mapping(source = "status", target = "status.statusId")
    @Mapping(source = "internalOrderRequest.orderItems", target = "orderItems")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "pickDate", ignore = true)
    InternalOrder toEntity(InternalOrderRequest internalOrderRequest, Long user, Integer status);

    @AfterMapping
    default void linkOrderItems(@MappingTarget InternalOrder internalOrder){
        internalOrder.getOrderItems().forEach(orderItem -> orderItem.setInternalOrder(internalOrder));
    }

    @Mapping(source = "materialId", target = "materialId.materialId")
    InternalOrderItem toInternalOrderItemEntity(OrderItemRequest orderItemRequest);

    Set<InternalOrderItem> toInternalOrderItemEntities(Set<OrderItemRequest> orderItemRequest);

    MaterialResponse toMaterialResponse(Material material);

    default OrderItemDto toOrderItemDto(InternalOrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDto.OrderItemDtoBuilder orderItemDto = OrderItemDto.builder()
                .orderItemId(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .materialId(toMaterialResponse(orderItem.getMaterialId()));
        return orderItemDto.build();
    }
    List<OrderItemDto> toOrderItemDtoList(List<InternalOrderItem> orderItems);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "orderItems", target = "orderItems")
    InternalOrderResponse toInternalOrderResponse(InternalOrder internalOrder);

    List<InternalOrderResponse> toInternalOrderResponseList(List<InternalOrder> internalOrders);
    UserDto toUserDto(User user);
    StatusDto toStatusDto(Status status);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="pickDate", target = "internalOrder.pickDate")
    InternalOrder partialUpdate(InternalOrderResponse internalOrderResponse, @MappingTarget InternalOrder internalOrder);
}
