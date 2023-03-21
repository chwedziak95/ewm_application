package com.kc6379.zarzadzaniemagazynem.mapper;

import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentDto;
import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentResponse;
import com.kc6379.zarzadzaniemagazynem.model.PersonalEquipment;
import org.mapstruct.*;




@Mapper(componentModel = "spring")
public abstract class PersonalEquipmentMapper {
    @Mapping(source = "userEquipment", target = "userEquipment.userId")
    public abstract PersonalEquipment toEntity(PersonalEquipmentDto personalEquipmentDto);


    public abstract PersonalEquipmentResponse toDto(PersonalEquipment personalEquipment);

}