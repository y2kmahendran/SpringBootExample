package com.techsu.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String homePage() {
        return "Welcome to Blinkist Application Developed by Mahe";
    }

}
