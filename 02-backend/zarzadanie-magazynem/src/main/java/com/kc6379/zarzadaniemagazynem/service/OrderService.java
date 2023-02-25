package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemRequest;
import com.kc6379.zarzadaniemagazynem.dto.OrderRequest;
import com.kc6379.zarzadaniemagazynem.dto.OrdersDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.OrdersMapper;
import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import com.kc6379.zarzadaniemagazynem.model.Orders;
import com.kc6379.zarzadaniemagazynem.model.Status;
import com.kc6379.zarzadaniemagazynem.repository.OrderItemRepository;
import com.kc6379.zarzadaniemagazynem.repository.OrderRepository;
import com.kc6379.zarzadaniemagazynem.repository.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthenticationService authenticationService;
    private final OrdersMapper ordersMapper;
    public void save(OrderRequest orderRequest){
        Status status = statusRepository.findByStatusId(1L).orElseThrow(() -> new EwmAppException("Nie znaleziono statusu o id"));
        String number = generateOrderNumber();
        Orders orders = ordersMapper.toEntity(orderRequest, authenticationService.getCurrentUser().getUserId() ,status.getStatusId(),number);
        orderRepository.save(orders);
        saveOrderItems(orderRequest.getOrderItems(), orders);
    }

    @Transactional
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        return ordersMapper.toOrdersDtoList(ordersList);
    }


    public void saveOrderItems(List<OrderItemRequest> orderItemRequests, Orders orders) {
        List<OrderItem> orderItems = ordersMapper.toOrderItemEntities(orderItemRequests);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrders(orders);
            orderItemRepository.save(orderItem);
        }
    }

    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }


}
