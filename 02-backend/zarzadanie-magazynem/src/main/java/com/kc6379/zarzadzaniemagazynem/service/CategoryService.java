package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.CategoryDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.CategoryMapper;
import com.kc6379.zarzadzaniemagazynem.model.Category;
import com.kc6379.zarzadzaniemagazynem.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Transactional
    public CategoryDto save(CategoryDto categoryDto){
        Category save = categoryRepository.save(categoryMapper.mapDtoToCategory(categoryDto));
        categoryDto.setCategoryId(save.getCategoryId());
        return categoryDto;
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDto)
                .collect(toList());
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono kategorii o ID - " + id));
        return categoryMapper.mapCategoryToDto(category);
    }
}
