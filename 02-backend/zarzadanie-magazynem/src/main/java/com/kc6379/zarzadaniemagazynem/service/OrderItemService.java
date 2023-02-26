package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.OrderItemMapper;
import com.kc6379.zarzadaniemagazynem.model.Orders;
import com.kc6379.zarzadaniemagazynem.repository.OrderItemRepository;
import com.kc6379.zarzadaniemagazynem.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemDto> getAllOrderItemsByOrder(Long ordersId){
        Orders orders = orderRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id"));
        return orderItemRepository.findByOrders(orders)
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
