package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

import java.time.Instant;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Material} entity
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
    private String unitOfMeasure;
    private String materialEAN;
    private Instant materialCreated;
    private Integer materialSafetyStock;
    private String materialDescription;
    private CategoryDto materialCategory;
    private VendorDto materialVendor;
    private Boolean materialStatus;
}