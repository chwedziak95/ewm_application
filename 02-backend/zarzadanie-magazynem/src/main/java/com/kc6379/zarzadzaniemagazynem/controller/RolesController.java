package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.RolesDto;
import com.kc6379.zarzadzaniemagazynem.service.RolesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
@Slf4j
public class RolesController {

    private final RolesService rolesService;

    @PostMapping
    public ResponseEntity<RolesDto> createRoles(@RequestBody RolesDto rolesDto){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(rolesService.save(rolesDto));
    }

    @GetMapping
    public ResponseEntity<List<RolesDto>> getAllRoles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rolesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDto> getRoles(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rolesService.getRoles(id));
    }
}
