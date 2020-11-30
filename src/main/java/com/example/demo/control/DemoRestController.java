package com.example.demo.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @GetMapping("/")
    public String hello() {
        return "Hello World! " + LocalDateTime.now();
    }

    @GetMapping("/demo")
    public String demo() {
        return "Hello Spring Boot!";
    }

    @GetMapping("/joda")
    public String joda() {
        return "Boot Spring Hello!";
    }
}
