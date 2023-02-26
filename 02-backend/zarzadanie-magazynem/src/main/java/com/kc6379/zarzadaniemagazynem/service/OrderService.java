package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.OrdersMapper;
import com.kc6379.zarzadaniemagazynem.model.*;
import com.kc6379.zarzadaniemagazynem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    private final UserRepository userRepository;
    private final MaterialRepository materialRepository;

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthenticationService authenticationService;
    private final OrdersMapper ordersMapper;
    private final VendorRepository vendorRepository;
    private final MailService mailService;

    public void save(OrderRequest orderRequest){
        Status status = statusRepository.findByStatusId(1L).orElseThrow(() -> new EwmAppException("Nie znaleziono statusu o id"));
        String number = generateOrderNumber();
        Orders orders = ordersMapper.toEntity(orderRequest, authenticationService.getCurrentUser().getUserId() ,status.getStatusId(),number);
        orderRepository.save(orders);
        saveOrderItems(orderRequest.getOrderItems(), orders);
    }
    public void saveOrderItems(List<OrderItemRequest> orderItemRequests, Orders orders) {
        List<OrderItem> orderItems = ordersMapper.toOrderItemEntities(orderItemRequests);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrders(orders);
            sendOrderItemToVendor(orderItem, orders);
            orderItemRepository.save(orderItem);
        }
    }

    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    private void sendOrderItemToVendor(OrderItem orderItem, Orders orders) {
        User user = userRepository.findByUserId(orders.getUser().getUserId()).orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o id"));
        Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId()).orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
        Vendor vendor = vendorRepository.findByVendorId(material.getMaterialVendor().getVendorId()).orElseThrow(() -> new EwmAppException("Nie znaleziono dostawcy o id"));
        mailService.sendOrderItemToVendor(new NotificationEmail("Zamówienie: " + orders.getOrderNumber(), vendor.getVendorEmail(), "Nowe zamówienie od: " + user.getFirstName() + " " + user.getLastName() + " na materiał: " + material.getMaterialName() + " w ilości: " + orderItem.getQuantity() + " " + material.getUnitOfMeasure()));
    }

    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }
}
