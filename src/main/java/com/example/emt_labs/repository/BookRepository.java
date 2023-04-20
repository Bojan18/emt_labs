package com.example.emt_labs.repository;

import com.example.emt_labs.model.Author;
import com.example.emt_labs.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(Author author);
    void deleteByName(String name);
}
