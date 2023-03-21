package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByVendorName(String vendorName);

    Optional<Vendor> findByVendorId(Long vendorId);

    Optional<Vendor> findByVendorEmailOrVendorNipOrVendorRegonOrVendorKrs(String vendorEmail, String vendorNip, String vendorRegon, String vendorKrs);
}
