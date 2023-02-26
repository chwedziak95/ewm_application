package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Material} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaterialResponse {
    private Long materialId;
    private String materialNumber;
    private String materialManufacturer;
    private String materialName;
    private Double materialPrice;
    private Integer materialQuantity;
    private String materialEAN;
    private Instant materialCreated;
    private Long materialSafetyStock;
    private String materialDescription;
    private CategoryDto materialCategory;
    private VendorDto materialVendor;
    private Boolean materialStatus;
}