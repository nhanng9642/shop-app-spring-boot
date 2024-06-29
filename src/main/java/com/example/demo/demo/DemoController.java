package com.example.demo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping ("/home")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/security")
    public String getSecurity() {
        return "Security";
    }

    @GetMapping("/api/v1/admin/secret")
    public String getAdminSecurity() {
        return "Admin security";
    }
}
