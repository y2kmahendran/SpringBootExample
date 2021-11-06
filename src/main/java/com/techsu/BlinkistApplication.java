package com.techsu;


import com.techsu.service.BookService;
import com.techsu.service.impl.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlinkistApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlinkistApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BookService bookService(){
        return new BookServiceImpl();
    }
}
