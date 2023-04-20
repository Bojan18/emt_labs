package com.example.emt_labs.service;

import com.example.emt_labs.model.Author;
import com.example.emt_labs.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long author_id);

    Optional<Author> saveDto (AuthorDto authorDto);

    Optional<Author> editDto (Long author_id, AuthorDto authorDto);

    void deleteById(Long author_id);

}
