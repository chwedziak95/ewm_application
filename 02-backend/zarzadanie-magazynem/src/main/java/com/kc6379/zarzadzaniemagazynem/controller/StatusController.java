package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.StatusDto;
import com.kc6379.zarzadzaniemagazynem.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@AllArgsConstructor
@Slf4j
public class StatusController {

    private final StatusService statusService;
    @PostMapping
    public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusService.save(statusDto));
    }

    @GetMapping
    public ResponseEntity<List<StatusDto>> getAllStatuses(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statusService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatus(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statusService.getStatus(id));
    }
}
