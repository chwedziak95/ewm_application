package com.kc6379.zarzadzaniemagazynem.repository;

import com.kc6379.zarzadzaniemagazynem.model.Category;
import com.kc6379.zarzadzaniemagazynem.model.Material;
import com.kc6379.zarzadzaniemagazynem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByMaterialId(Long materialId);

    List<Material> findAllByMaterialCategory(Category materialCategory);

    List<Material> findAllByMaterialVendor(Vendor materialVendor);

    boolean existsByMaterialNumber(String materialNumber);

    boolean existsByMaterialName(String materialName);

    boolean existsByMaterialEAN(String materialEAN);
}
