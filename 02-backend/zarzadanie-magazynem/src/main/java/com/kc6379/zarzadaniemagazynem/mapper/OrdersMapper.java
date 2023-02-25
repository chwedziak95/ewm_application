package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import com.kc6379.zarzadaniemagazynem.model.Orders;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorMapper.class})
public interface OrdersMapper {

    @Mapping(source = "ordersRequest.vendor", target = "vendor.vendorId")
    @Mapping(source = "user", target = "user.userId")
    @Mapping(source = "status", target = "status.statusId")
    @Mapping(source = "ordersRequest.orderItems", target = "orderItems")
    @Mapping(target = "orderDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "deliveryDate", ignore = true)
    @Mapping(source = "orderNumber", target = "orderNumber")
    Orders toEntity(OrderRequest ordersRequest, Long user, Long status, String orderNumber);

    /*@Mapping(source = "vendor.vendorId", target = "vendor")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderRequest toDto(Orders orders);*/

    List<OrdersDto> toOrdersDtoList(List<Orders> ordersList);

    @Mapping(source = "status.name", target = "statusName")
    OrdersDto toDto(Orders orders);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Orders orders) {
        orders.getOrderItems().forEach(orderItem -> orderItem.setOrders(orders));
    }

   /* @Mapping(source = "vendor.vendorId", target = "vendor")
    @Mapping(source = "user.userId", target = "user")
    @Mapping(source = "status.statusId", target = "status")
    @Mapping(source = "orderItems", target = "orderItems")
    OrdersResponse toDto(Orders orders);

    @Mapping(source = "userId", target = "userId")
    UserEqDto mapEq(Long userId);

    default VendorDto map(Long value) {
        if (value == null) {
            return null;
        }

        VendorDto dto = new VendorDto();
        dto.setVendorId(value);
        return dto;
    }

    default Long map(Vendor value) {
        if (value == null) {
            return null;
        }

        return value.getVendorId();
    }

    default StatusDto mapSt(Long value) {
        if (value == null) {
            return null;
        }

        StatusDto statusDto = new StatusDto();
        statusDto.setStatusId(value);
        return statusDto;
    }*/




    /*List<OrderRequest> toDtos(List<Orders> orders);*/

    @Mapping(source = "materialId", target = "materialId.materialId")
    @Mapping(target = "internalOrderId", ignore = true)
    OrderItem toOrderItemEntity(OrderItemRequest orderItemRequest);

    List<OrderItem> toOrderItemEntities(List<OrderItemRequest> orderItemRequest);
    @Mapping(source = "materialId.materialId", target = "materialId")
    OrderItemRequest toOrderItemDto(OrderItem orderItem);

    List<OrderItemRequest> toOrderItemDtos(List<OrderItem> orderItems);

}
