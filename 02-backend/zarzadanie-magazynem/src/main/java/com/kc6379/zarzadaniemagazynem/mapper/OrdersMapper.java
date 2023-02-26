package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import com.kc6379.zarzadaniemagazynem.model.Orders;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(source = "user", target = "user.userId")
    @Mapping(source = "status", target = "status.statusId")
    @Mapping(source = "ordersRequest.orderItems", target = "orderItems")
    @Mapping(target = "orderDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "deliveryDate", ignore = true)
    @Mapping(source = "orderNumber", target = "orderNumber")
    Orders toEntity(OrderRequest ordersRequest, Long user, Long status, String orderNumber);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Orders orders) {
        orders.getOrderItems().forEach(orderItem -> orderItem.setOrders(orders));
    }

    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem toOrderItemEntity(OrderItemRequest orderItemRequest);

    List<OrderItem> toOrderItemEntities(List<OrderItemRequest> orderItemRequest);
    @Mapping(source = "materialId.materialId", target = "materialId")
    OrderItemRequest toOrderItemDto(OrderItem orderItem);
    List<OrderItemRequest> toOrderItemDtos(List<OrderItem> orderItems);
    OrdersResponse toDto(Orders orders);
}
