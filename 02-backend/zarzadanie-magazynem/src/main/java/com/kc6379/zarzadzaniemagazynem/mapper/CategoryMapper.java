package com.kc6379.zarzadaniemagazynem.mapper;

import com.kc6379.zarzadaniemagazynem.dto.CategoryDto;
import com.kc6379.zarzadaniemagazynem.dto.VendorDto;
import com.kc6379.zarzadaniemagazynem.model.Category;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category mapDtoToCategory(CategoryDto categoryDto);

    CategoryDto mapCategoryToDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);
}