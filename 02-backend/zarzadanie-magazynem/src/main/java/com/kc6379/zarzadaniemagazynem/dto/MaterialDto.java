package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Material} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaterialDto {
    private Long materialId;
    private String materialNumber;
    private String materialManufacturer;
    private String materialName;
    private Double materialPrice;
    private Integer materialQuantity;
    private String unitOfMeasure;
    private String materialEAN;
    private Integer materialSafetyStock;
    private String materialDescription;
    private Long materialCategory;
    private Long materialVendor;
}