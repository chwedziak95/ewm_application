package com.kc6379.zarzadzaniemagazynem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User userEquipment;

}