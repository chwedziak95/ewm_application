package com.kc6379.zarzadaniemagazynem.controller;

import com.kc6379.zarzadaniemagazynem.dto.OrderRequest;
import com.kc6379.zarzadaniemagazynem.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> getAllOrders(){
        orderService.getAllOrders();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
