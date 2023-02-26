package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.MaterialDto;
import com.kc6379.zarzadaniemagazynem.model.Material;
import com.kc6379.zarzadaniemagazynem.dto.MaterialResponse;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE,componentModel = "spring")
public abstract class MaterialMapper {
    public abstract MaterialResponse toDto(Material material);

    @Mapping(source = "materialCategory", target = "materialCategory.categoryId")
    @Mapping(source = "materialVendor", target = "materialVendor.vendorId")
    @Mapping(target = "materialCreated", expression = "java(java.time.Instant.now())")
    @Mapping(target = "materialStatus", constant = "true")
    public abstract Material toEntity(MaterialDto materialDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "materialCategory", target = "materialCategory.categoryId")
    @Mapping(source = "materialVendor", target = "materialVendor.vendorId")
    public abstract Material partialUpdate(MaterialDto materialDto, @MappingTarget Material material);
}
