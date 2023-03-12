package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.model.InternalOrder;
import com.kc6379.zarzadaniemagazynem.model.InternalOrderItem;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(unmappedTargetPolicy = IGNORE,componentModel = "spring")
public interface InternalOrderMapper {
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

    @Mapping(source = "internalOrderItem.id", target ="orderItemId")
    Set<OrderItemDto> toInternalOrderItemDtos(Set<InternalOrderItem> internalOrderItems);

    default InternalOrderResponse toDto(InternalOrder internalOrder){
        InternalOrderResponse internalOrderResponse = new InternalOrderResponse();
        internalOrderResponse.setId(internalOrder.getInternalOrderId());
        internalOrderResponse.setUser(new UserEqDto(internalOrder.getUser().getUserId(), internalOrder.getUser().getEmail(), internalOrder.getUser().getFirstName(), internalOrder.getUser().getLastName()));
        internalOrderResponse.setStatus(new StatusDto(internalOrder.getStatus().getStatusId(), internalOrder.getStatus().getName()));
        internalOrderResponse.setPickLocation(internalOrder.getPickUpLocation());
        internalOrderResponse.setOrderDateTIme(internalOrder.getOrderDate());
        internalOrderResponse.setPickupDateTime(internalOrder.getPickDate());
        internalOrderResponse.setOrderItems(toInternalOrderItemDtos(internalOrder.getOrderItems()));

        return internalOrderResponse;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InternalOrder partialUpdate(InternalOrderResponse internalOrderResponse, @MappingTarget InternalOrder internalOrder);
}
