package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.PersonalEquipmentDto;
import com.kc6379.zarzadaniemagazynem.dto.PersonalEquipmentResponse;
import com.kc6379.zarzadaniemagazynem.model.PersonalEquipment;
import org.mapstruct.*;




@Mapper(componentModel = "spring")
public abstract class PersonalEquipmentMapper {
    @Mapping(source = "userEquipment", target = "userEquipment.userId")
    public abstract PersonalEquipment toEntity(PersonalEquipmentDto personalEquipmentDto);


    public abstract PersonalEquipmentResponse toDto(PersonalEquipment personalEquipment);

}