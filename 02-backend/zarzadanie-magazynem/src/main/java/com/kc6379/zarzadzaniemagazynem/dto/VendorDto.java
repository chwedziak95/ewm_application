package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Vendor} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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