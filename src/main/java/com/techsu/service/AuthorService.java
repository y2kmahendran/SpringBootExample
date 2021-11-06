package com.techsu.service;

import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Author;

import java.util.List;

public interface AuthorService {

    Author saveAuthor(Author author);

    List<Author> saveAuthors(List<Author> authors);

    List<Author> getAuthors();

    Author getAuthorById(Long id) throws ResourceNotFoundException;

    String deleteAuthor(Long id);




}
