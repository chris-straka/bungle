package dev.cstraka.bungle.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/")
    public String getHello() {
        System.out.println("getHello()");
        return "hello";
    }
}
