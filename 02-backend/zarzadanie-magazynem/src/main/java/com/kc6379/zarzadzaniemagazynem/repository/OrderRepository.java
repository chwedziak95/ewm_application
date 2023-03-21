package com.kc6379.zarzadzaniemagazynem.repository;

import com.kc6379.zarzadzaniemagazynem.model.Orders;
import com.kc6379.zarzadzaniemagazynem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByOrdersId(Long ordersId);

    List<Orders> findAllByUser(User user);

}
