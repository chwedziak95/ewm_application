package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.MaterialDto;
import com.kc6379.zarzadzaniemagazynem.dto.MaterialResponse;
import com.kc6379.zarzadzaniemagazynem.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/material")
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    public ResponseEntity<Void> createMaterial(@RequestBody MaterialDto materialDto){
        materialService.save(materialDto);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Void> updateMaterial(@PathVariable Long id, @RequestBody MaterialDto materialDto){
        materialService.update(id, materialDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAllMaterials(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(materialService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> getMaterial(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(materialService.getMaterial(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<MaterialResponse>> getMaterialByCategory(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(materialService.getMaterialByCategory(id));
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<List<MaterialResponse>> getMaterialByVendor(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(materialService.getMaterialByVendor(id));
    }
}
