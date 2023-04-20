package com.example.emt_labs.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue
    private Long author_id;
    private String name;
    private String surname;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Country country;
    public Author() {
    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
