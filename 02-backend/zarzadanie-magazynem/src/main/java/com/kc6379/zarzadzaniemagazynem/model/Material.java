package com.kc6379.zarzadzaniemagazynem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
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
    private String materialLocation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "categoryId",
            referencedColumnName = "categoryId"
    )
    private Category materialCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "vendorId",
            referencedColumnName = "vendorId"
    )
    private Vendor materialVendor;
    private Boolean materialStatus;
}
