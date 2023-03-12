package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.InternalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternalOrderRepository extends JpaRepository<InternalOrder, Long> {
    Optional<InternalOrder> findByInternalOrderId(Long internalOrderId);
}
