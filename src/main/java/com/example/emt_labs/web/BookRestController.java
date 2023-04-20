package com.example.emt_labs.web;

import com.example.emt_labs.model.Book;
import com.example.emt_labs.model.dto.BookDto;
import com.example.emt_labs.repository.AuthorRepository;
import com.example.emt_labs.repository.BookRepository;
import com.example.emt_labs.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookRestController(BookService bookService,
                              AuthorRepository authorRepository,
                              BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    private List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(book)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.saveDto(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    //Dto -> Data Transfer Object, ako sakame da smeneme so se prakja do API app,
    //togas menjame samo vo Dto a da ne mora da menjame i vo controllerot
    //RequestBody -> site podatoci se kako request params, name=a, category=NOVEL...
    //se toa e vo URL, nikogas ne e enkriptirano!!! -> ovoa e loso

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.editDto(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Book> rent(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.rentBookDto(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/condition/{id}")
    public ResponseEntity<Book> changeCondition(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.changeCondition(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    //ke bide kopce so change condition/or smth like that.

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        bookService.deleteById(id);
        if (bookService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }

}
