package com.example.emt_labs.model;

import com.example.emt_labs.enumerations.Category;
import com.example.emt_labs.enumerations.Condition;
import com.example.emt_labs.enumerations.RentalStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long book_id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;
    private int availableCopies;
    @Enumerated(value = EnumType.STRING)
    private RentalStatus rentalStatus;
    @Enumerated(value = EnumType.STRING)
    private Condition condition;
    public Book() {
    }

    public Book(String name, Category category, Author author, int availableCopies, RentalStatus rentalStatus, Condition condition) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rentalStatus = rentalStatus;
        this.condition = condition;
    }
}
