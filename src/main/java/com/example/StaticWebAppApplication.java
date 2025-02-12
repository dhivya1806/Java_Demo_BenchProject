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
        model.addAttribute("title", "My CICD Bench Project Usecase-102");
        model.addAttribute("msg", "This application is integrated with Jenkins and Deployed onto Kubernetes by M Dhivya on 12 Feb");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(StaticWebAppApplication.class, args);
    }

}
