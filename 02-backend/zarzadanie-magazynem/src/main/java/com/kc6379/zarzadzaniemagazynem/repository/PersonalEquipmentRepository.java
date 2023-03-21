package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.PersonalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface PersonalEquipmentRepository extends JpaRepository<PersonalEquipment, Long> {
    Optional<PersonalEquipment> findAllByUserEquipment(Long userId);

}
