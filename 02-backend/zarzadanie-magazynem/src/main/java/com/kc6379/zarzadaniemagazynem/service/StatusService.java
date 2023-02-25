package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.StatusDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.StatusMapper;
import com.kc6379.zarzadaniemagazynem.model.Status;
import com.kc6379.zarzadaniemagazynem.repository.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Transactional
    public StatusDto save(StatusDto statusDto){
        Status save = statusRepository.save(statusMapper.toEntity(statusDto));
        statusDto.setStatusId(save.getStatusId());
        return statusDto;
    }

    @Transactional(readOnly = true)
    public List<StatusDto> getAll() {
        return statusRepository.findAll()
                .stream()
                .map(statusMapper::toDto)
                .collect(toList());
    }

    public StatusDto getStatus(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono statusu o ID - " + id));
        return statusMapper.toDto(status);
    }
}
