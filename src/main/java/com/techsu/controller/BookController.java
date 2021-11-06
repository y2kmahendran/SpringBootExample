package com.techsu.controller;


import com.techsu.dto.BookDto;
import com.techsu.dto.BookResponse;
import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Book;
import com.techsu.service.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/singleBook")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookDto bookDto) {
        log.debug("Request{}", bookDto);
        var bookRequest = convertToEntity(bookDto);
        var book = bookService.saveBook(bookRequest);
        var bookResponse = convertToResponseDto(book);
        bookResponse.setAuthorName(book.getAuthor().getName());
        bookResponse.setCategoryName(book.getCategory().getName());
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @PostMapping
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return bookService.saveBooks(books);
    }

    @GetMapping
    public List<BookResponse> findAllBooks() {
        return bookService.getBooks().stream().map(book -> modelMapper.map(book, BookResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findBookById(@RequestParam(value = "id",required = true) Long id) throws ResourceNotFoundException {
        var book = bookService.getBookById(id);
        var bookResponse = convertToResponseDto(book);
        return ResponseEntity.ok().body(bookResponse);
    }

    @GetMapping("/author/{authorName}")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "authorName",required = true) String authorName) throws ResourceNotFoundException{
        return bookService.searchBookByAuthor(authorName);

    }

    @GetMapping("/category/{categoryName}")
    public List<Book> searchBooksByCategory(@Valid @RequestParam(value = "category",required = true) String categoryName) throws ResourceNotFoundException {
        return bookService.searchBookByCategory(categoryName);

    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> updateBookById(@Valid @PathVariable Long id) throws ResourceNotFoundException {
        var book = bookService.updateBookById(id);
        BookDto bookResponse = convertToDto(book);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@Valid @RequestParam Long id) {
        log.info("Deleting the book {}", id);
        return bookService.deleteBook(id);
    }

    private BookDto convertToDto(Book book){
        BookDto bookDto = modelMapper.map(book,BookDto.class);
        return bookDto;
    }

    public Book convertToEntity(BookDto bookDto){
        Book book = modelMapper.map(bookDto,Book.class);
        return  book;
    }

    public BookResponse convertToResponseDto(Book book){
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return bookResponse;
    }

}
