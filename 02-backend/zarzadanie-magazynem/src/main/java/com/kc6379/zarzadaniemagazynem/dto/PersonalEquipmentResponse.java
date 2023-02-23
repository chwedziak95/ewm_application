package com.kc6379.zarzadaniemagazynem.dto;

import com.kc6379.zarzadaniemagazynem.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.PersonalEquipment} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalEquipmentResponse {
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
    private UserEqDto userEquipment;
}