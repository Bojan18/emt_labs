package com.example.emt_labs.service.Impl;
import com.example.emt_labs.exceptions.CountryNotFoundException;
import com.example.emt_labs.model.Country;
import com.example.emt_labs.model.dto.CountryDto;
import com.example.emt_labs.repository.CountryRepository;
import com.example.emt_labs.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long country_id) {
        return countryRepository.findById(country_id);
    }

    @Override
    public Optional<Country> saveDto(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());

        countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> editDto(Long country_id, CountryDto countryDto) {
        Country country = countryRepository.findById(country_id).orElseThrow(CountryNotFoundException::new);

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long country_id) {
        countryRepository.deleteById(country_id);
    }
}
