package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @EntityGraph(attributePaths = {"vendor", "user", "status", "orderItems"})
    Optional<Orders> findById(Long id);
}
