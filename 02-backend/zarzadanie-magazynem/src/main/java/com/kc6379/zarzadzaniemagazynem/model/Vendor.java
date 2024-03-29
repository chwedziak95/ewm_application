package com.kc6379.zarzadzaniemagazynem.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;
    @NotBlank(message = "Nazwa dostawcy jest wymagana")
    private String vendorName;
    @NotBlank(message = "Adres dostawcy jest wymagany")
    private String vendorAddress;
    @NotBlank(message = "Miasto dostawcy jest wymagane")
    private String vendorCity;
    @NotBlank(message = "Kod pocztowy dostawcy jest wymagany")
    private String vendorPostalCode;
    @NotBlank(message = "Kraj dostawcy jest wymagany")
    private String vendorCountry;
    @NotBlank(message = "Email dostawcy jest wymagany")
    @Email
    private String vendorEmail;
    private String vendorPhone;
    private String vendorNip;
    private String vendorRegon;
    private String vendorKrs;
    @NotBlank(message = "Numer konta dostawcy jest wymagany")
    private String vendorBankAccount;
    @NotBlank(message = "Nazwa banku dostawcy jest wymagana")
    private String vendorBankName;
    private Instant vendorCreated;
}
