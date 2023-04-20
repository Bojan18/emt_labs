package com.example.emt_labs.web;

import com.example.emt_labs.enumerations.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/condition")
public class ConditionRestController {
    @GetMapping
    private ResponseEntity<Condition[]> findAll(){
        return ResponseEntity.ok(Condition.values());
    }
}
