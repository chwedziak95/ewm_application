package com.kc6379.zarzadzaniemagazynem.repository;

import com.kc6379.zarzadzaniemagazynem.model.InternalOrder;
import com.kc6379.zarzadzaniemagazynem.model.InternalOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InternalOrderItemRepository extends JpaRepository<InternalOrderItem, Long> {
    Set<InternalOrderItem> findByInternalOrder (InternalOrder internalOrder);
}
