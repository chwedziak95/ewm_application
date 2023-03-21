package com.kc6379.zarzadaniemagazynem.controller;

import com.kc6379.zarzadaniemagazynem.dto.VendorDto;
import com.kc6379.zarzadaniemagazynem.service.VendorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendor")
@AllArgsConstructor
@Slf4j
public class VendorController {

    private final VendorService vendorService;
    @PostMapping
    public ResponseEntity<VendorDto> createVendor(@RequestBody VendorDto vendorDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendorService.save(vendorDto));
    }

    @PostMapping("/update")
    public ResponseEntity<VendorDto> updateVendor(@RequestBody VendorDto vendorDto){
        vendorService.updateVendor(vendorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> getAllVendors(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vendorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDto> getVendor(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vendorService.getVendor(id));
    }
}
