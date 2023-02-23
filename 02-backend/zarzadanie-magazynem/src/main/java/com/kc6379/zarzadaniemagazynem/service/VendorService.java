package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.VendorDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.VendorMapper;
import com.kc6379.zarzadaniemagazynem.model.Vendor;
import com.kc6379.zarzadaniemagazynem.repository.VendorRepository;
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
public class VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;
    @Transactional
    public VendorDto save(VendorDto vendorDto){
        var existingVendor = vendorRepository.findByVendorEmailOrVendorNipOrVendorRegonOrVendorKrs(vendorDto.getVendorEmail(), vendorDto.getVendorNip(), vendorDto.getVendorRegon(), vendorDto.getVendorKrs());
        if (existingVendor.isPresent()) {
            Vendor existing = existingVendor.get();
            List<String> alreadyExistProperties = new ArrayList<>();
            if (existing.getVendorEmail().equals(vendorDto.getVendorEmail())) {
                alreadyExistProperties.add("Email");
            }
            if (existing.getVendorNip().equals(vendorDto.getVendorNip())) {
                alreadyExistProperties.add("NIP");
            }
            if (existing.getVendorRegon().equals(vendorDto.getVendorRegon())) {
                alreadyExistProperties.add("REGON");
            }
            if (existing.getVendorKrs().equals(vendorDto.getVendorKrs())) {
                alreadyExistProperties.add("KRS");
            }
            String message = "W bazie danych znajduje się dostawca o tych parametrach: " + String.join(", ", alreadyExistProperties);
            throw new EwmAppException(message);
        }

        Vendor save = vendorRepository.save(vendorMapper.mapDtoToVendor(vendorDto));
        vendorDto.setVendorId(save.getVendorId());
        return vendorDto;
    }

    @Transactional(readOnly = true)
    public List<VendorDto> getAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::mapVendorToDto)
                .collect(toList());
    }

    public VendorDto getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono dostawcy o ID - " + id));
        return vendorMapper.mapVendorToDto(vendor);
    }
}
