package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.*;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.OrdersMapper;
import com.kc6379.zarzadzaniemagazynem.model.*;
import com.kc6379.zarzadzaniemagazynem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@SuppressWarnings("SpellCheckingInspection")
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
    private final MailService mailService;
    private final OrdersResponse orderResponse;

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
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException(" Podczas przeliczania Nie znaleziono materiału o id"));
            total += material.getMaterialPrice() * orderItem.getQuantity();
        }
        return total;
    }

    public void saveOrderItems(Set<OrderItemRequest> orderItemRequests, Orders orders) {
        Set<OrderItem> orderItems = ordersMapper.toOrderItemEntities(orderItemRequests);
        Map<Vendor, List<OrderItem>> vendorOrderItemsMap = orderItems.stream().collect(Collectors.groupingBy(orderItem -> {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
            return material.getMaterialVendor();
        }));

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrders(orders);
            orderItemRepository.save(orderItem);
        }

        for (Map.Entry<Vendor, List<OrderItem>> entry : vendorOrderItemsMap.entrySet()) {
            sendOrderItemsToVendor(entry.getKey(), entry.getValue(), orders);
        }
    }

    private void sendOrderItemsToVendor(Vendor vendor, List<OrderItem> orderItems, Orders orders) {
        User user = userRepository.findByUserId(orders.getUser().getUserId())
                .orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o id"));

        List<OrderItemDto> orderItemsDto = convertOrderItemsToDto(orderItems);

        mailService.sendOrderItemToVendor(new NotificationEmail(
                "Zamówienie: " + orders.getOrderNumber(), vendor.getVendorEmail(),
                "Nowe zamówienie od: " + user.getFirstName() +
                        " " + user.getLastName() +
                        "\nNumer zamówienia: " + orders.getOrderNumber()), orderItemsDto);
    }

    private List<OrderItemDto> convertOrderItemsToDto(List<OrderItem> orderItems) {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));

            MaterialResponse materialResponse = MaterialResponse.builder()
                    .materialNumber(material.getMaterialNumber())
                    .materialName(material.getMaterialName())
                    .unitOfMeasure(material.getUnitOfMeasure())
                    .build();

            OrderItemDto orderItemDto = OrderItemDto.builder()
                    .orderItemId(orderItem.getOrderItemId())
                    .materialId(materialResponse)
                    .quantity(orderItem.getQuantity())
                    .build();

            orderItemDtos.add(orderItemDto);
        }

        return orderItemDtos;
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

    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }

    public void deliveryOrder(Long ordersId, OrdersResponse ordersResponse) {
        Orders orders = orderRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id" + ordersId));
        LocalDate orderDate = orders.getOrderDate();
        LocalDate deliveryDate = ordersResponse.getDeliveryDate();
        if (deliveryDate.isBefore(orderDate)) {
            throw new EwmAppException("Data dostawy nie może być wcześniejsza niż data zamówienia");
        } else if (deliveryDate.isAfter(LocalDate.now())) {
            throw new EwmAppException("Data dostawy nie może być późniejsza niż dzisiaj");
        }
        orderResponse.setOrdersId(ordersId);
        Status status = statusRepository.findByName("Dostarczono").orElseThrow();
        orders.setStatus(status);
        orderResponse.setDeliveryDate(deliveryDate);
        if (orders.getDeliveryDate() != null) {
            throw new EwmAppException("Zamówienie zostało już dostarczone");
        }
        updateMaterialQuantity(orders);
        ordersMapper.partialUpdate(orderResponse, orders);
        orderRepository.save(orders);
    }

    private void updateMaterialQuantity(Orders orders) {
        Set<OrderItem> orderItems = orderItemRepository.findByOrders(orders);
        for (OrderItem orderItem : orderItems) {
            Material material = materialRepository.findByMaterialId(orderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
            int newMaterialQuantity = material.getMaterialQuantity() + orderItem.getQuantity();
            material.setMaterialQuantity(newMaterialQuantity);
            materialRepository.save(material);
        }
    }

    public void cancelOrder(Long ordersId) {
        Orders orders = orderRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id" + ordersId));
        if (orders.getDeliveryDate() == null && !Objects.equals(orders.getStatus().getName(), "Anulowano")){
            Status status = statusRepository.findByName("Anulowano").orElseThrow();
            orders.setStatus(status);
            orderRepository.save(orders);
        }else{
            throw new EwmAppException("Wystąpił bład podczas anulowania zamówienia o id: " + ordersId);
        }
    }

    public OrdersResponse getOrder(Long id) {
        Orders orders = orderRepository.findAllByOrdersId(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia o id: " + id));
        return ordersMapper.toOrdersResponse(orders);
    }
}
