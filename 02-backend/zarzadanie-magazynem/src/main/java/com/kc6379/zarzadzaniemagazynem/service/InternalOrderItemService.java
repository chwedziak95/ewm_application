package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.InternalOrderItemMapper;
import com.kc6379.zarzadaniemagazynem.model.InternalOrder;
import com.kc6379.zarzadaniemagazynem.repository.InternalOrderItemRepository;
import com.kc6379.zarzadaniemagazynem.repository.InternalOrderRepository;
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
