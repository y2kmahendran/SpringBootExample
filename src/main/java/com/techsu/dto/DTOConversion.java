package com.techsu.dto;

import com.techsu.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOConversion {

    @Autowired
    public ModelMapper modelMapper;

    private Book convertToModelEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }


//    public Book convertBookEntity()
}
