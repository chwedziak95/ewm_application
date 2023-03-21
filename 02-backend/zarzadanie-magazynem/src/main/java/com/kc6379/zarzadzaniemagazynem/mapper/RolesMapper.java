package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.RolesDto;
import com.kc6379.zarzadzaniemagazynem.model.Roles;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolesMapper {
    Roles mapDtoToRoles(RolesDto rolesDto);

    RolesDto mapRolesToDto(Roles roles);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Roles partialUpdate(RolesDto rolesDto, @MappingTarget Roles roles);
}