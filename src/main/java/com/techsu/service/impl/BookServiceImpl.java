package com.techsu.service.impl;

import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Author;
import com.techsu.model.Book;
import com.techsu.model.Category;
import com.techsu.repository.AuthorRepository;
import com.techsu.repository.BookRepository;
import com.techsu.repository.CategoryRepository;
import com.techsu.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;



public class BookServiceImpl implements BookService {

    Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Book saveBook(Book book) {

        Optional<Author> auth = authorRepository.findAll().stream()
                .filter(author -> author.getName().equals(book.getAuthor().getName())).findFirst();
        if (auth.isPresent()) {
            book.setAuthor(auth.get());
        } else {
            book.setAuthor(book.getAuthor());
        }

        Optional<Category> category = categoryRepository.findAll().stream()
                .filter(cate -> cate.getName().equals(book.getCategory().getName())).findFirst();
        if (category.isPresent()) {
            book.setCategory(category.get());
        } else {
            book.setCategory(book.getCategory());
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Id not found"));
    }


    @Override
    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        log.info("deleted by book by id {}", id);
        return "Book deleted ->" + id;
    }


    @Override
    public Book updateBookById(Long id) throws ResourceNotFoundException {
        var book = new Book();
        if (bookRepository.findById(id).isPresent()) {
            book.setName(book.getName());
        } else {
            throw new ResourceNotFoundException("Book Id not found");
        }

        return bookRepository.save(book);
    }



    @Override
    public List<Book> searchBookByAuthor(String authorName) {
        return bookRepository.searchBookByAuthor(authorName);
    }

    @Override
    public List<Book> searchBookByCategory(String categoryName) {
        return bookRepository.searchBookByCategory(categoryName);
    }


}
