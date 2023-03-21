package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.kc6379.zarzadzaniemagazynem.model.Roles} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolesDto {
    private Long roleId;
    private String name;
}