package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentDto;
import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentResponse;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.PersonalEquipmentMapper;
import com.kc6379.zarzadzaniemagazynem.model.PersonalEquipment;
import com.kc6379.zarzadzaniemagazynem.repository.PersonalEquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PersonalEquipmentService {

    private final PersonalEquipmentRepository personalEquipmentRepository;

    private final PersonalEquipmentMapper personalEquipmentMapper;

    public void save(PersonalEquipmentDto personalEquipmentDto){
        personalEquipmentRepository.save(personalEquipmentMapper.toEntity(personalEquipmentDto));
    }

    @Transactional(readOnly = true)
    public PersonalEquipmentResponse getPersonalEquipment(Long id){
        PersonalEquipment personalEquipment = personalEquipmentRepository.findById(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono sprzętu o id: " + id));
        return personalEquipmentMapper.toDto(personalEquipment);
    }

    public List<PersonalEquipmentResponse> getAll(){
        return personalEquipmentRepository.findAll()
                .stream()
                .map(personalEquipmentMapper::toDto)
                .collect(toList());
    }

    public List<PersonalEquipmentResponse> getAllByUserId(Long userId){
        return personalEquipmentRepository.findAllByUserEquipment(userId)
                .stream()
                .map(personalEquipmentMapper::toDto)
                .collect(toList());
    }

}
