package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.RolesDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.RolesMapper;
import com.kc6379.zarzadzaniemagazynem.model.Roles;
import com.kc6379.zarzadzaniemagazynem.repository.RolesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class RolesService {
    private final RolesRepository rolesRepository;
    private final RolesMapper rolesMapper;

    @Transactional
    public RolesDto save(RolesDto rolesDto){
        Roles save = rolesRepository.save(rolesMapper.mapDtoToRoles(rolesDto));
        rolesDto.setRoleId(save.getRoleId());
        return rolesDto;
    }

    @Transactional(readOnly = true)
    public List<RolesDto> getAll() {
        return rolesRepository.findAll()
                .stream()
                .map(rolesMapper::mapRolesToDto)
                .collect(toList());
    }

    public RolesDto getRoles(Long id) {
        Roles roles = rolesRepository.findByRoleId(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono roli o ID - " + id));
        return rolesMapper.mapRolesToDto(roles);
    }


}
