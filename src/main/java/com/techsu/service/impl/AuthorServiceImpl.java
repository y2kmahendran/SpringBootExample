package com.techsu.service.impl;

import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Author;
import com.techsu.repository.AuthorRepository;
import com.techsu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    Author author;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> saveAuthors(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) throws ResourceNotFoundException {
        return authorRepository.findById(Math.toIntExact(id)).orElseThrow();
    }

    @Override
    public String deleteAuthor(Long id) {
        authorRepository.deleteById(Math.toIntExact(id));
        return "author deleted" + id;
    }




}
