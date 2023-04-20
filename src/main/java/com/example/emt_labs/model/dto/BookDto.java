package com.example.emt_labs.model.dto;

import com.example.emt_labs.enumerations.Category;
import com.example.emt_labs.enumerations.Condition;
import com.example.emt_labs.enumerations.RentalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private Category category;
    private Long author_id;
    private int availableCopies;
    private RentalStatus rentalStatus;
    private Condition condition;
    public BookDto() {
    }

    public BookDto(String name, Category category, Long author_id, int availableCopies, RentalStatus rentalStatus, Condition condition) {
        this.name = name;
        this.category = category;
        this.author_id = author_id;
        this.availableCopies = availableCopies;
        this.rentalStatus = rentalStatus;
        this.condition = condition;
    }

}
