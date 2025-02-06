package com.example.demo;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class StaticWebAppApplication {
    public static void main(String[] args) {
SpringApplication.run(StaticWebAppApplication.class, args);
System.out.println("Spring Boot Static Web App running at http://localhost:8080");
    }
}