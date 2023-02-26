package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.Category;
import com.kc6379.zarzadaniemagazynem.model.Material;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByMaterialId(Long materialId);

    Optional<Material> findByMaterialName(String materialName);

    List<Material> findAllByMaterialCategory(Category materialCategory);

    List<Material> findAllByMaterialVendor(Vendor materialVendor);

    Optional<Material> findByMaterialNumberOrMaterialNameOrMaterialEAN(String materialNumber, String materialName, String materialEAN);
}
