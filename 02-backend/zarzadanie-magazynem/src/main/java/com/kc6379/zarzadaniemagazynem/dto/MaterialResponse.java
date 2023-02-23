package com.kc6379.zarzadaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Material} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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