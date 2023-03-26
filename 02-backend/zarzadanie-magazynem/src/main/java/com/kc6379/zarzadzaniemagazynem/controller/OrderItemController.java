package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/by-order/{ordersId}")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItemsByOrderId(@PathVariable Long ordersId){
        return ResponseEntity
                .status(OK)
                .body(orderItemService.getAllOrderItemsByOrder(ordersId));
    }
}
