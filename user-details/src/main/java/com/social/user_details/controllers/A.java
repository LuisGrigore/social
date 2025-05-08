package com.social.user_details.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class A {
    @GetMapping
    public String a(HttpServletRequest request){

        return request.getHeader("id");
    }
}
