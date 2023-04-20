package com.example.emt_labs.config;

import com.example.emt_labs.enumerations.Category;
import com.example.emt_labs.enumerations.Condition;
import com.example.emt_labs.enumerations.RentalStatus;
import com.example.emt_labs.model.Author;
import com.example.emt_labs.model.Book;
import com.example.emt_labs.model.Country;
import com.example.emt_labs.repository.AuthorRepository;
import com.example.emt_labs.repository.BookRepository;
import com.example.emt_labs.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository,
                           AuthorRepository authorRepository,
                           BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init(){
        //country, author, book
        for (int i = 1; i <= 3; i++) {
            countryRepository.save(new Country("Country: " + i, "Continent: " + i));
            List<Country> countryList = countryRepository.findAll();
            authorRepository.save(new Author("AuthorName: " + i, "AuthorSurname: " + i, countryList.get(i-1)));
            List<Author> authorList = authorRepository.findAll();
            bookRepository.save(new Book("Book: " + i, Category.DRAMA, authorList.get(i-1), 3, RentalStatus.AVAILABLE, Condition.GOOD));
        }
        List<Country> countryList = countryRepository.findAll();
        authorRepository.save(new Author("AuthorName: " + 4, "AuthorSurname: " + 4, countryList.get(1)));
    }

}
