package com.kids.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping
    public String hello() {
        return "Hello, World!";
    }
}
