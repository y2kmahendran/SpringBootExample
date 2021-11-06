package com.techsu.service;

import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {
    Book saveBook(Book book);

    List<Book> saveBooks(List<Book> books);

    List<Book> getBooks();

    Book getBookById(Long id) throws ResourceNotFoundException;

    String deleteBook(Long id);

    Book updateBookById(Long id) throws ResourceNotFoundException;


    List<Book> searchBookByAuthor(String authorName) throws ResourceNotFoundException;

    List<Book> searchBookByCategory(String categoryName) throws ResourceNotFoundException;
}
