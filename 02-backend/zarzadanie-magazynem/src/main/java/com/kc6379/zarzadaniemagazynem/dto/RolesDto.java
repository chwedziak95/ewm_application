package com.kc6379.zarzadaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Roles} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolesDto {
    private Integer roleId;
    private String name;
}