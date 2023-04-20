package com.example.emt_labs.service;
import com.example.emt_labs.model.Country;
import com.example.emt_labs.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long country_id);

    Optional<Country> saveDto (CountryDto countryDto);

    Optional<Country> editDto (Long country_id, CountryDto countryDto);

    void deleteById(Long country_id);
}
