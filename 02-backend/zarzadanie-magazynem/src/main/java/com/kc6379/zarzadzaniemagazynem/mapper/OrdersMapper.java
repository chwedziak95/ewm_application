package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.*;
import com.kc6379.zarzadzaniemagazynem.model.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;


import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
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

    MaterialResponse toMaterialResponse(Material material);

    default OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDto.OrderItemDtoBuilder orderItemDto = OrderItemDto.builder()
                .orderItemId(orderItem.getOrderItemId())
                .quantity(orderItem.getQuantity())
                .materialId(toMaterialResponse(orderItem.getMaterialId()));
        return orderItemDto.build();
    }

    List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "orderItems", target = "orderItems")
    OrdersResponse toOrdersResponse(Orders orders);

    List<OrdersResponse> toOrdersResponseList(List<Orders> orders);
    UserDto toUserDto(User user);
    StatusDto toStatusDto(Status status);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", ignore = true)
    void partialUpdate(OrdersResponse orderResponse, @MappingTarget Orders orders);
}
