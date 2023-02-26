package com.kc6379.zarzadaniemagazynem.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEqDto {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
}