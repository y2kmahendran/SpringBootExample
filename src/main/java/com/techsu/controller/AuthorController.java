package com.techsu.controller;

import com.techsu.dto.AuthorDto;
import com.techsu.dto.BookDto;
import com.techsu.dto.BookResponse;
import com.techsu.exception.ResourceNotFoundException;
import com.techsu.model.Author;
import com.techsu.model.Book;
import com.techsu.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    Logger log = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    AuthorService authorService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        log.debug("Request{}", authorDto);
        var authorRequest = convertToEntity(authorDto);
        var author = authorService.saveAuthor(authorRequest);
        var authorResponse = convertToDto(author);
        return new ResponseEntity<>(authorResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public List<AuthorDto> findAllAuthors() {
        return authorService.getAuthors().stream().map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable Long id) throws ResourceNotFoundException {
        var author = authorService.getAuthorById(id);
        AuthorDto authorResponse = convertToDto(author);
        return ResponseEntity.ok().body(authorResponse);
    }



    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        log.info("Deleting the Author {}", id);
        return authorService.deleteAuthor(id);
    }

    private AuthorDto convertToDto(Author author){
        AuthorDto authorDto = modelMapper.map(author,AuthorDto.class);
        return authorDto;
    }

    public Author convertToEntity(AuthorDto authorDto){
        Author author = modelMapper.map(authorDto,Author.class);
        return  author;
    }


}
