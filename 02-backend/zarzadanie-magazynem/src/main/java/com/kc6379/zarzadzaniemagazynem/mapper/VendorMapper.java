package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.VendorDto;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorDto mapVendorToDto(Vendor vendor);

    @InheritInverseConfiguration
    @Mapping(target = "vendorCreated", expression = "java(java.time.Instant.now())")
    Vendor mapDtoToVendor(VendorDto vendorDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vendor partialUpdate(VendorDto vendorDto, @MappingTarget Vendor vendor);
}