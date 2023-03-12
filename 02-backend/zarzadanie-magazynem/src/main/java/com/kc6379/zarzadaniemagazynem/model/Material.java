package com.kc6379.zarzadaniemagazynem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "categoryId",
            referencedColumnName = "categoryId"
    )
    private Category materialCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "vendorId",
            referencedColumnName = "vendorId"
    )
    private Vendor materialVendor;
    private Boolean materialStatus;
}
