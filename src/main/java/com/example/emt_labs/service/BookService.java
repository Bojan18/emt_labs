package com.example.emt_labs.service;

import com.example.emt_labs.enumerations.Category;
import com.example.emt_labs.enumerations.Condition;
import com.example.emt_labs.enumerations.RentalStatus;
import com.example.emt_labs.model.Book;
import com.example.emt_labs.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long book_id);
    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Category category, Long author_id, int availableCopies, RentalStatus rentalStatus, Condition condition);
    Optional<Book> saveDto(BookDto bookDto);

    Optional<Book> edit(Long book_id, String name, Category category, Long author_id, int availableCopies);

//    Optional<Book> rentBook(Long book_id);

    Optional<Book> rentBookDto(Long book_id, BookDto bookDto);

    Optional<Book> editDto(Long book_id, BookDto bookDto);

    Optional<Book> changeCondition(Long book_id, BookDto bookDto);

    void deleteById(Long book_id);

}
