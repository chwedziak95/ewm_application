package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.VendorDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.VendorMapper;
import com.kc6379.zarzadzaniemagazynem.model.Vendor;
import com.kc6379.zarzadzaniemagazynem.repository.VendorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

@Service
@AllArgsConstructor
@Slf4j
public class VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Transactional
    public VendorDto save(VendorDto vendorDto) {
        // Check each identifier on its own and skip empty optional ones (NIP/REGON/KRS),
        // so a vendor without e.g. a KRS number is not reported as a duplicate.
        List<String> alreadyExistProperties = new ArrayList<>();
        if (hasText(vendorDto.getVendorEmail()) && vendorRepository.existsByVendorEmail(vendorDto.getVendorEmail())) {
            alreadyExistProperties.add("Email");
        }
        if (hasText(vendorDto.getVendorNip()) && vendorRepository.existsByVendorNip(vendorDto.getVendorNip())) {
            alreadyExistProperties.add("NIP");
        }
        if (hasText(vendorDto.getVendorRegon()) && vendorRepository.existsByVendorRegon(vendorDto.getVendorRegon())) {
            alreadyExistProperties.add("REGON");
        }
        if (hasText(vendorDto.getVendorKrs()) && vendorRepository.existsByVendorKrs(vendorDto.getVendorKrs())) {
            alreadyExistProperties.add("KRS");
        }
        if (!alreadyExistProperties.isEmpty()) {
            String message = "W bazie danych znajduje się dostawca o tych parametrach: " + String.join(", ", alreadyExistProperties);
            throw new EwmAppException(message);
        }

        Vendor save = vendorRepository.save(vendorMapper.mapDtoToVendor(vendorDto));
        vendorDto.setVendorId(save.getVendorId());
        return vendorDto;
    }

    public void updateVendor(Long id, VendorDto vendorDto){
        Vendor vendor = vendorRepository.findByVendorId(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono dostawcy o id: " + id));
        vendorRepository.save(vendorMapper.partialUpdate(vendorDto, vendor));

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
