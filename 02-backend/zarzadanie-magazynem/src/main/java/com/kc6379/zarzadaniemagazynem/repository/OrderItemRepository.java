package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
