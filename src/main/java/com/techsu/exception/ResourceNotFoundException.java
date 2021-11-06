package com.techsu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    public static final long serialVersionUID = 1L;

    public ResourceNotFoundException(){
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
