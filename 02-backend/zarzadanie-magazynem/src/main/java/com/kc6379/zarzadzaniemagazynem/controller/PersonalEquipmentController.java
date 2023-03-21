package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentDto;
import com.kc6379.zarzadzaniemagazynem.dto.PersonalEquipmentResponse;
import com.kc6379.zarzadzaniemagazynem.service.PersonalEquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/personal-equipment")
@AllArgsConstructor
public class PersonalEquipmentController {
    private final PersonalEquipmentService personalEquipmentService;

    @PostMapping
    public ResponseEntity<Void> createPersonalEquipment(@RequestBody PersonalEquipmentDto personalEquipmentDto){
        personalEquipmentService.save(personalEquipmentDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonalEquipmentResponse>> getAllPersonalEquipment(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personalEquipmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalEquipmentResponse> getPersonalEquipment(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personalEquipmentService.getPersonalEquipment(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PersonalEquipmentResponse>> getPersonalEquipmentByUser(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personalEquipmentService.getAllByUserId(id));
    }
}
