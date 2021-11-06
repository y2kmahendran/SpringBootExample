package com.techsu.dto;

import lombok.*;



@Getter
@Setter
public class AuthorDto {

    private int id;
    private String name;
    private String type;
    private boolean availability;
}