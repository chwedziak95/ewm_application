package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Vendor} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VendorDto {
    private Long vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorCity;
    private String vendorPostalCode;
    private String vendorCountry;
    private String vendorEmail;
    private String vendorPhone;
    private String vendorNip;
    private String vendorRegon;
    private String vendorKrs;
    private String vendorBankAccount;
    private String vendorBankName;
}