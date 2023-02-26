package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.UserEqDto;
import com.kc6379.zarzadaniemagazynem.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEqDto toDto(User user);

    @InheritInverseConfiguration
    User fromDto(UserEqDto userDto);
}
