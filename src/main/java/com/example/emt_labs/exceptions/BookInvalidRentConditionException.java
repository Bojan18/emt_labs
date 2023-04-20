package com.example.emt_labs.exceptions;

public class BookInvalidRentConditionException extends RuntimeException{
    public BookInvalidRentConditionException(String name) {
        super(String.format("%s cannot be rented because the condition is Damaged.", name));
    }
}
