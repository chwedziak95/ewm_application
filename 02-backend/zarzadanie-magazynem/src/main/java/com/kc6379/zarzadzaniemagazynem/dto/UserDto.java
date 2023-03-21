package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
}