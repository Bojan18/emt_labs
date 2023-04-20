package com.example.emt_labs.web;

import com.example.emt_labs.enumerations.RentalStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/rentalStatus")
public class RentalStatusRestController {
    @GetMapping
    private ResponseEntity<RentalStatus[]> findAll(){
        return ResponseEntity.ok(RentalStatus.values());
    }
}
