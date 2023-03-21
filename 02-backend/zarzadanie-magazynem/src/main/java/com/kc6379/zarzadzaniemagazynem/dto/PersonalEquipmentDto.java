package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import java.util.Date;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.PersonalEquipment} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalEquipmentDto {
    private Long personalEquipmentId;
    private String name;
    private String number;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private Date purchaseDate;
    private Double purchasePrice;
    private Date warrantyDate;
    private String invoice;
    private Long userEquipment;
}