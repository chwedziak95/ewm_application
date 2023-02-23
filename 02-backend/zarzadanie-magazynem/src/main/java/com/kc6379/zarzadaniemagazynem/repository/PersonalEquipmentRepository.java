package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.PersonalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalEquipmentRepository extends JpaRepository<PersonalEquipment, Long> {
    Optional<PersonalEquipment> findAllByUserEquipment(Long userId);

}
