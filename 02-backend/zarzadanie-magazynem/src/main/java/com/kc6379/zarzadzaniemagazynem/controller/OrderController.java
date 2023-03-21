package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.OrderRequest;
import com.kc6379.zarzadzaniemagazynem.dto.OrdersResponse;
import com.kc6379.zarzadzaniemagazynem.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.save(orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersResponse> getOrder(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrder(id));
    }

    @PostMapping("/delivery/{ordersId}")
    public ResponseEntity<Void> deliveryOrder(@PathVariable Long ordersId, @RequestBody OrdersResponse ordersResponse){
        orderService.deliveryOrder(ordersId, ordersResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel/{ordersId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long ordersId){
        orderService.cancelOrder(ordersId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}