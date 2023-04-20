package com.example.emt_labs.web;
import com.example.emt_labs.model.Country;
import com.example.emt_labs.model.dto.CountryDto;
import com.example.emt_labs.repository.CountryRepository;
import com.example.emt_labs.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryRepository countryRepository;
    private final CountryService countryService;

    public CountryRestController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @GetMapping
    private List<Country> findAll() {
        return countryRepository.findAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<Country> findById(@PathVariable Long id) {
        return countryRepository.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    private ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return countryService.saveDto(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    private ResponseEntity<Country> save(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        return countryService.editDto(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        countryService.deleteById(id);
        if(countryService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }
}
