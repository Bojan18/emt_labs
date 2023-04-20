package com.example.emt_labs.service.Impl;

import com.example.emt_labs.enumerations.Category;
import com.example.emt_labs.enumerations.Condition;
import com.example.emt_labs.enumerations.RentalStatus;
import com.example.emt_labs.exceptions.AuthorNotFoundException;
import com.example.emt_labs.exceptions.BookAlreadyRentedException;
import com.example.emt_labs.exceptions.BookInvalidRentConditionException;
import com.example.emt_labs.exceptions.BookNotFoundException;
import com.example.emt_labs.model.Author;
import com.example.emt_labs.model.Book;
import com.example.emt_labs.model.dto.BookDto;
import com.example.emt_labs.repository.AuthorRepository;
import com.example.emt_labs.repository.BookRepository;
import com.example.emt_labs.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long book_id) {
        return bookRepository.findById(book_id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Long author_id, int availableCopies, RentalStatus rentalStatus, Condition condition) {
        Author author = authorRepository.findById(author_id).orElseThrow(AuthorNotFoundException::new);
        RentalStatus currStatus = RentalStatus.RENTED;
        if(availableCopies > 0){
            currStatus = RentalStatus.AVAILABLE;
        }
        Book book = new Book(name, category, author, availableCopies, currStatus, Condition.GOOD);
        //sea za sea site knigi se spremni za izdavanje

        bookRepository.deleteByName(name);

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> saveDto(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor_id()).orElseThrow(AuthorNotFoundException::new);
        RentalStatus currStatus = RentalStatus.RENTED;
        if(bookDto.getAvailableCopies() > 0){
            currStatus = RentalStatus.AVAILABLE;
        }

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies(), currStatus, Condition.GOOD);

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long book_id, String name, Category category, Long author_id, int availableCopies) {
        Book book = bookRepository.findById(book_id).orElseThrow(BookNotFoundException::new);

        book.setName(name);
        book.setCategory(category);

        Author author = authorRepository.findById(author_id).orElseThrow(AuthorNotFoundException::new);

        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        bookRepository.save(book);

        return Optional.of(book);
    }

//    @Override
//    public Optional<Book> rentBook(Long book_id) {
//        Book book = bookRepository.findById(book_id).orElseThrow(BookNotFoundException::new);
//
//        if(book.getAvailableCopies() == 0){
//            throw new BookAlreadyRentedException(book.getName());
//        }
//
//        book.setAvailableCopies(book.getAvailableCopies()-1);
//        //assuming a user can rent only 1 book
//
//        bookRepository.save(book);
//        return Optional.of(book);
//    }

    @Override
    public Optional<Book> changeCondition(Long book_id, BookDto bookDto) {
        Book book = bookRepository.findById(book_id).orElseThrow(BookNotFoundException::new);

        Condition con = null;
        if(bookDto.getCondition().equals(Condition.GOOD)){
            con = Condition.DAMAGED;
        } else if(bookDto.getCondition().equals(Condition.DAMAGED)){
            con = Condition.GOOD;
        }

        book.setCondition(con);

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> rentBookDto(Long book_id, BookDto bookDto) {
        Book book = bookRepository.findById(book_id).orElseThrow(BookNotFoundException::new);

        if(bookDto.getAvailableCopies() <= 0){
            bookDto.setRentalStatus(RentalStatus.RENTED);
            throw new BookAlreadyRentedException(book.getName(), bookDto.getRentalStatus());
        }

        if(book.getCondition().equals(Condition.DAMAGED)){
            throw new BookInvalidRentConditionException(book.getName());
        }

        book.setAvailableCopies(bookDto.getAvailableCopies()-1);
        if(book.getAvailableCopies() <= 0){
            book.setRentalStatus(RentalStatus.RENTED);
        }

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> editDto(Long book_id, BookDto bookDto) {
        Book book = bookRepository.findById(book_id).orElseThrow(BookNotFoundException::new);
        Author author = authorRepository.findById(bookDto.getAuthor_id()).orElseThrow(AuthorNotFoundException::new);

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());

        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());


        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long book_id) {
        this.bookRepository.deleteById(book_id);
    }
}
