package com.example.emt_labs.model.dto;

import com.example.emt_labs.model.Country;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AuthorDto {
    private Long author_id;
    private String name;
    private String surname;
    private Country country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
