package com.social.user_details.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A {
    @GetMapping
    public String a(){
        return "A";
    }
}
