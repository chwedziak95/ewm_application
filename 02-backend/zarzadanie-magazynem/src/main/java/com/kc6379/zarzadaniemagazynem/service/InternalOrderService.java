package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.*;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.InternalOrderMapper;
import com.kc6379.zarzadaniemagazynem.mapper.MaterialMapper;
import com.kc6379.zarzadaniemagazynem.model.*;
import com.kc6379.zarzadaniemagazynem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class InternalOrderService {

    private final UserRepository userRepository;
    private final MaterialRepository materialRepository;
    private final StatusRepository statusRepository;
    private final InternalOrderRepository internalOrderRepository;
    private final InternalOrderItemRepository internalOrderItemRepository;
    private final AuthenticationService authenticationService;
    private final InternalOrderMapper internalOrderMapper;
    private final InternalOrderResponse internalOrderResponse;
    private final MaterialDto materialDto;
    private final MaterialMapper materialMapper;
    private OrderRequest orderRequest;
    private final OrderService orderService;
    private OrderItemRequest orderItemRequest;

    public void save(InternalOrderRequest internalOrderRequest){
        Status status = statusRepository.findByName("Utworzono").orElseThrow(() -> new EwmAppException("Nie znakleziono statusu o nazwie Utworzono"));
        InternalOrder internalOrder = internalOrderMapper.toEntity(internalOrderRequest, authenticationService.getCurrentUser().getUserId() ,status.getStatusId());
        boolean check = availableCheck(internalOrderRequest.getOrderItems());
        if (check ==true) {
            internalOrderRepository.save(internalOrder);
            saveInternalOrderItems(internalOrderRequest.getOrderItems(), internalOrder);
        } else {
            throw new EwmAppException("Nie można utworzyć tego zamówienia");
        }
    }

    private boolean availableCheck(Set<OrderItemRequest> orderItemRequests) {
        boolean checkResult = true;
        Set<InternalOrderItem> orderItems = internalOrderMapper.toInternalOrderItemEntities(orderItemRequests);
        for (InternalOrderItem internalOrderItem : orderItems){
            Material material = materialRepository.findByMaterialId(internalOrderItem.getMaterialId().getMaterialId()).orElseThrow(() -> new EwmAppException("Brak materiału"));
            if (internalOrderItem.getQuantity() > material.getMaterialQuantity()){
                checkResult = false;
            }
        }
        return checkResult;
    }

    private void saveInternalOrderItems(Set<OrderItemRequest> orderItemRequests, InternalOrder internalOrder) {
        Set<InternalOrderItem> orderItems = internalOrderMapper.toInternalOrderItemEntities(orderItemRequests);
        for (InternalOrderItem internalOrderItem : orderItems){
            internalOrderItem.setInternalOrder(internalOrder);
            internalOrderItemRepository.save(internalOrderItem);
        }
    }
    @Transactional
    public List<InternalOrderResponse> getAll() {
        return internalOrderRepository.findAll()
                .stream()
                .map(internalOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<InternalOrderResponse> getAllByUser(Long userId){
        User user = userRepository.findByUserId(userId).
                orElseThrow(() -> new EwmAppException("Nie znaleziono uytkownika o id: " + userId));
        return internalOrderRepository.findAllByUser(user)
               .stream()
               .map(internalOrderMapper::toDto)
               .collect(Collectors.toList());
    }

    public void withdraw(Long id){
        InternalOrder internalOrder = internalOrderRepository.findByInternalOrderId(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono zamówienia do magazynu o id: " + id));
        if (internalOrder.getPickDate() != null){
            throw new EwmAppException("Wygląda na to, że zamówienie " + id + " zostało już wydane");
            }else{
            updateMaterialQuantity(internalOrder);
            internalOrderResponse.setPickupDateTime(LocalDateTime.now());
            Status status = statusRepository.findByName("Wydane").orElseThrow();
            internalOrderResponse.setStatus(status);
            internalOrderMapper.partialUpdate(internalOrderResponse, internalOrder);
            internalOrderRepository.save(internalOrder);
        }
        internalOrderMapper.partialUpdate(internalOrderResponse, internalOrder);
        internalOrderRepository.save(internalOrder);
    }

    private void updateMaterialQuantity(InternalOrder internalOrder) {
        Set<InternalOrderItem> orderItems = internalOrderItemRepository.findByInternalOrder(internalOrder);
        for(InternalOrderItem internalOrderItem : orderItems){
            Material material = materialRepository.findByMaterialId(internalOrderItem.getMaterialId().getMaterialId())
                    .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o id"));
            materialDto.setMaterialQuantity(material.getMaterialQuantity() - internalOrderItem.getQuantity());
            materialMapper.partialUpdate(materialDto, material);
            materialRepository.save(material);
            needCheck(material, internalOrder.getInternalOrderId());
        }
    }

    private void needCheck(Material material, Long id) {
        if(material.getMaterialQuantity() < material.getMaterialSafetyStock()){
            Integer qty = material.getMaterialSafetyStock() - material.getMaterialQuantity();
            orderItemRequest = new OrderItemRequest(material.getMaterialId(), qty);
            Set<OrderItemRequest> set = new HashSet<>();
            set.add(orderItemRequest);
            String com = "Zamówienie stworzone automatycznie, podczas wydania: " + id + ". Stan magazynowy spadł poniżej ustawionego minimum";
            orderRequest = new OrderRequest(null, null,com,set);
            orderService.save(orderRequest);
        }
    }

    public void cancelOrder(Long id){
        InternalOrder internalOrder = internalOrderRepository.findByInternalOrderId(id).orElseThrow();
        Status status = statusRepository.findByName("Anulowano").orElseThrow();
        if (internalOrder.getPickDate() == null && !Objects.equals(internalOrder.getStatus().getName(), "Anulowano")) {
            changeStatus(internalOrder, status);
        }
    }

    public void ready(Long id){
        InternalOrder internalOrder = internalOrderRepository.findByInternalOrderId(id).orElseThrow();
        Status status = statusRepository.findByName("Gotowe").orElseThrow();
        if (internalOrder.getPickDate() == null && Objects.equals(internalOrder.getStatus().getName(), "Utworzono")) {
            changeStatus(internalOrder, status);
        }
    }

    private void changeStatus(InternalOrder internalOrder, Status status){
        internalOrderResponse.setStatus(status);
        internalOrderMapper.partialUpdate(internalOrderResponse, internalOrder);
        internalOrderRepository.save(internalOrder);
    }
}
