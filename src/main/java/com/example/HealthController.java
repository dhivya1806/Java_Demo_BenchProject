package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health")
    public String livenessCheck() {
        return "Application is running";
    }

    @GetMapping("/ready")
    public String readinessCheck() {
        return "Ready to receive request";
    }
}
