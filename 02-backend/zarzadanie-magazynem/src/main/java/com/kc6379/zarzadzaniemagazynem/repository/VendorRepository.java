package com.kc6379.zarzadzaniemagazynem.repository;

import com.kc6379.zarzadzaniemagazynem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByVendorName(String vendorName);

    Optional<Vendor> findByVendorId(Long vendorId);

    boolean existsByVendorEmail(String vendorEmail);

    boolean existsByVendorNip(String vendorNip);

    boolean existsByVendorRegon(String vendorRegon);

    boolean existsByVendorKrs(String vendorKrs);
}
