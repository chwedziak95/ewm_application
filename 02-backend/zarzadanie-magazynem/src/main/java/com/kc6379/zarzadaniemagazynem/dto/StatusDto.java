package com.kc6379.zarzadaniemagazynem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kc6379.zarzadaniemagazynem.model.Status} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDto implements Serializable {
    private Long statusId;
    private String name;
}