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

    @GetMapping
    public ResponseEntity<List<InternalOrderResponse>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(internalOrderService.getAll());
    }

    @PostMapping("withdraw/{internalOrderId}")
    public ResponseEntity<Void> withdraw(@PathVariable Long internalOrderId){
        internalOrderService.withdraw(internalOrderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
