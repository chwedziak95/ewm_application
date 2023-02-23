package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.RolesDto;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.mapper.RolesMapper;
import com.kc6379.zarzadaniemagazynem.model.Roles;
import com.kc6379.zarzadaniemagazynem.repository.RolesRepository;
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

    public RolesDto getRoles(Integer id) {
        Roles roles = rolesRepository.findByRoleId(id)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono roli o ID - " + id));
        return rolesMapper.mapRolesToDto(roles);
    }


}
