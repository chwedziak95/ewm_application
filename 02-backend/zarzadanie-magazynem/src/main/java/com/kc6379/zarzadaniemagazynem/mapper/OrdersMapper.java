package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import com.kc6379.zarzadaniemagazynem.model.Orders;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(unmappedTargetPolicy = IGNORE,componentModel = "spring")
public interface OrdersMapper {

    @Mapping(source = "user", target = "user.userId")
    @Mapping(source = "status", target = "status.statusId")
    @Mapping(source = "ordersRequest.orderItems", target = "orderItems")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "total", target = "orderTotal")
    Orders toEntity(OrderRequest ordersRequest, Long user, Integer status, String orderNumber, Double total);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Orders orders) {
        orders.getOrderItems().forEach(orderItem -> orderItem.setOrders(orders));
    }

    @Mapping(source = "materialId", target = "materialId.materialId")
    OrderItem toOrderItemEntity(OrderItemRequest orderItemRequest);

    Set<OrderItem> toOrderItemEntities(Set<OrderItemRequest> orderItemRequest);
    Set<OrderItemDto> toOrderItemDtos(Set<OrderItem> orderItems);
    OrdersResponse toDto(Orders orders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Orders partialUpdate(OrdersResponse orderResponse, @MappingTarget Orders orders);
}
