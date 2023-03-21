package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.InternalOrderItemMapper;
import com.kc6379.zarzadzaniemagazynem.model.InternalOrder;
import com.kc6379.zarzadzaniemagazynem.repository.InternalOrderItemRepository;
import com.kc6379.zarzadzaniemagazynem.repository.InternalOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InternalOrderItemService {

    private final InternalOrderItemRepository internalOrderItemRepository;
    private final InternalOrderRepository internalOrderRepository;
    private final InternalOrderItemMapper internalOrderItemMapper;

    public List<OrderItemDto> getAllInternalOrderItemsbyInternalOrder(Long internalOrderId){
        InternalOrder internalOrder = internalOrderRepository.findByInternalOrderId(internalOrderId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono wewnętrznego zamówienia o id: " + internalOrderId));
        return internalOrderItemRepository.findByInternalOrder(internalOrder)
                .stream()
                .map(internalOrderItemMapper::toDto)
                .collect(Collectors.toList());
    }

}
