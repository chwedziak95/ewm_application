package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.OrderItemMapper;
import com.kc6379.zarzadzaniemagazynem.model.Orders;
import com.kc6379.zarzadzaniemagazynem.repository.OrderItemRepository;
import com.kc6379.zarzadzaniemagazynem.repository.OrderRepository;
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
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id" + ordersId));
        return orderItemRepository.findByOrders(orders)
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
