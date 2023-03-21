package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.MaterialDto;
import com.kc6379.zarzadzaniemagazynem.dto.MaterialResponse;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.MaterialMapper;
import com.kc6379.zarzadzaniemagazynem.model.Category;
import com.kc6379.zarzadzaniemagazynem.model.Material;
import com.kc6379.zarzadzaniemagazynem.model.Vendor;
import com.kc6379.zarzadzaniemagazynem.repository.CategoryRepository;
import com.kc6379.zarzadzaniemagazynem.repository.MaterialRepository;
import com.kc6379.zarzadzaniemagazynem.repository.VendorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;


    public void save(MaterialDto materialDto){
        var existingMaterial = materialRepository.findByMaterialNumberOrMaterialNameOrMaterialEAN(materialDto.getMaterialNumber(), materialDto.getMaterialName(), materialDto.getMaterialEAN());
        if (existingMaterial.isPresent()) {
            Material existing = existingMaterial.get();
            List<String> alreadyExistProperties = new ArrayList<>();

            if (existing.getMaterialNumber().equals(materialDto.getMaterialNumber())) {
                alreadyExistProperties.add("Numer Materiału");
            }

            if (existing.getMaterialName().equals(materialDto.getMaterialName())) {
                alreadyExistProperties.add("Nazwa Materiału");
            }

            if (!materialDto.getMaterialEAN().isEmpty() && existing.getMaterialEAN().equals(materialDto.getMaterialEAN())) {
                alreadyExistProperties.add("EAN materiału");
            }

            if (!alreadyExistProperties.isEmpty()) {
                String message = "W bazie danych znajduje się materiał o tych parametrach: " + String.join(", ", alreadyExistProperties);
                throw new EwmAppException(message);
            }
        }
        materialRepository.save(materialMapper.toEntity(materialDto));
    }


    @Transactional(readOnly = true)
    public List<MaterialResponse> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(materialMapper::toDto)
                .collect(toList());
    }

    public MaterialResponse getMaterial(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o ID - " + id));
        return materialMapper.toDto(material);
    }

    public List<MaterialResponse> getMaterialByVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono dostawcy o ID - " + vendorId));
        List<Material> materials = materialRepository.findAllByMaterialVendor(vendor);
        return materials.stream().map(materialMapper::toDto).collect(toList());
    }

    public List<MaterialResponse> getMaterialByCategory(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono kategorii o ID - " + categoryId));
        List<Material> materials = materialRepository.findAllByMaterialCategory(category);
        return materials.stream().map(materialMapper::toDto).collect(toList());
    }

    public MaterialResponse getMaterialByName(String name) {
        Material material = materialRepository.findByMaterialName(name)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono materiału o nazwie - " + name));
        return materialMapper.toDto(material);
    }


}
