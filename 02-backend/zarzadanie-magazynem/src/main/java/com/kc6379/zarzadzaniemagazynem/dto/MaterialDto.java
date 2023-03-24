package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Material} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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