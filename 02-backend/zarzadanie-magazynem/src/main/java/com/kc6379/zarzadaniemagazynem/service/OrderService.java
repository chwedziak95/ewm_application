package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.MaterialMapper;
import com.kc6379.zarzadaniemagazynem.mapper.OrdersMapper;
import com.kc6379.zarzadaniemagazynem.model.*;
import com.kc6379.zarzadaniemagazynem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
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
    private final OrdersResponse orderResponse;
    private final MaterialDto materialDto;
    private final MaterialMapper materialMapper;

    public void save(OrderRequest orderRequest){
        Status status = statusRepository.findByStatusId(1).orElseThrow(() -> new EwmAppException("Nie znaleziono statusu o id"));
        String number = generateOrderNumber();
        Double total = countTotal(orderRequest.getOrderItems());
        Orders orders = ordersMapper.toEntity(orderRequest, authenticationService.getCurrentUser().getUserId() ,status.getStatusId(),number, total);
        orderRepository.save(orders);
        saveOrderItems(orderRequest.getOrderItems(), orders);
    }

    private Double countTotal(Set<OrderItemRequest> orderItemRequests) {
        Set<OrderItem> orderItems = ordersMapper.toOrderItemEntities(orderItemRequests);
        Double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException(" Podczas przeliczania Nie znaleziono materiału o id"));
            total += material.getMaterialPrice() * orderItem.getQuantity();
        }
        return total;
    }

    public void saveOrderItems(Set<OrderItemRequest> orderItemRequests, Orders orders) {
        Set<OrderItem> orderItems = ordersMapper.toOrderItemEntities(orderItemRequests);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrders(orders);
            sendOrderItemToVendor(orderItem, orders);
            orderItemRepository.save(orderItem);
        }
    }

    public List<OrdersResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(ordersMapper::toOrdersResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<OrdersResponse> getAllByUser(Long userId){
        User user = userRepository.findByUserId(userId).
                orElseThrow(() -> new EwmAppException("Nie znaleziono uytkownika o id: " + userId));
        return orderRepository.findAllByUser(user)
                .stream()
                .map(ordersMapper::toOrdersResponse)
                .collect(Collectors.toList());
    }

    private void sendOrderItemToVendor(OrderItem orderItem, Orders orders) {
        User user = userRepository.findByUserId(orders.getUser().getUserId()).orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o id"));
        Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId()).orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
        Vendor vendor = vendorRepository.findByVendorId(material.getMaterialVendor().getVendorId()).orElseThrow(() -> new EwmAppException("Nie znaleziono dostawcy o id"));
        mailService.sendOrderItemToVendor(new NotificationEmail(
                "Zamówienie: " + orders.getOrderNumber(), vendor.getVendorEmail(),
                "Nowe zamówienie od: " + user.getFirstName() +
                        " " + user.getLastName() +
                        " na materiał: numer artykułu: " +
                        material.getMaterialNumber() +
                        " nazwa artykułu:  " +
                        material.getMaterialName() +
                        " w ilości: " +
                        orderItem.getQuantity() +
                        material.getUnitOfMeasure()));
    }

    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }

    public void deliveryOrder(Long ordersId) {
        orderResponse.setOrdersId(ordersId);
        orderResponse.setDeliveryDate(LocalDate.now());
        Status status = statusRepository.findByName("Dostarczono").orElseThrow();
        orderResponse.setStatus(status);
        Orders orders = orderRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id" + ordersId));
        if (orders.getDeliveryDate() != null ){
            throw new EwmAppException("Zamówienie zostało już dostarczone");
        }else {
            updateMaterialQuantity(orders);
            ordersMapper.partialUpdate(orderResponse, orders);
            orderRepository.save(orders);
        }
    }

    private void updateMaterialQuantity(Orders orders) {
        Set<OrderItem> orderItems = orderItemRepository.findByOrders(orders);
        for (OrderItem orderItem : orderItems) {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
            materialDto.setMaterialQuantity(material.getMaterialQuantity() + orderItem.getQuantity());
            materialMapper.partialUpdate(materialDto, material);
            materialRepository.save(material);
        }
    }

    public void cancelOrder(Long ordersId) {
        Orders orders = orderRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id" + ordersId));
        if (orders.getDeliveryDate() == null && !Objects.equals(orders.getStatus().getName(), "Anulowano")){
            Status status = statusRepository.findByName("Anulowano").orElseThrow();
            orderResponse.setStatus(status);
            ordersMapper.partialUpdate(orderResponse, orders);
            orderRepository.save(orders);
        }else{
            throw new EwmAppException("Wystąpił bład podczas anulowania zamówienia o id: " + ordersId);
        }
    }
}
