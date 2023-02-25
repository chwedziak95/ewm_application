package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatusId(Long statusId);
    Optional<Status> findByName(String name);
}
