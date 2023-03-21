package com.kc6379.zarzadzaniemagazynem.repository;

import com.kc6379.zarzadzaniemagazynem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatusId(Integer statusId);
    Optional<Status> findByName(String name);
}
