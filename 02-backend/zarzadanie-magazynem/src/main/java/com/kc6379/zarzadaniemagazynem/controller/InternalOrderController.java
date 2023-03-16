package com.kc6379.zarzadaniemagazynem.controller;

import com.kc6379.zarzadaniemagazynem.dto.InternalOrderRequest;
import com.kc6379.zarzadaniemagazynem.dto.InternalOrderResponse;
import com.kc6379.zarzadaniemagazynem.service.InternalOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internal-orders")
@AllArgsConstructor
public class InternalOrderController {
    private final InternalOrderService internalOrderService;
    @PostMapping
    public ResponseEntity<Void> createInternalOrder(@RequestBody InternalOrderRequest internalOrderRequest){
        internalOrderService.save(internalOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("withdraw/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable Long id){
        internalOrderService.withdraw(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id){
        internalOrderService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("ready/{id}")
    public ResponseEntity<Void> ready(@PathVariable Long id){
        internalOrderService.ready(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InternalOrderResponse>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(internalOrderService.getAll());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<InternalOrderResponse>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(internalOrderService.getAllByUser(userId));
    }



}
