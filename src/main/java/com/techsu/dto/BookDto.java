package com.techsu.dto;


import com.techsu.model.Author;
import com.techsu.model.Book;
import com.techsu.model.Category;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDto {

    private Long id;
    private String name;
    private String synopsis;
    private Category category;
    private Author author;

}
