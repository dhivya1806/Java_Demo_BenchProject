package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StaticWebAppApplication {

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "My ECS Project");
        model.addAttribute("msg", "This is the test application using AWS CICD tools on ECS with fine grained limited access 3");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(StaticWebAppApplication.class, args);
    }

}
