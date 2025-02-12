package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
 @GetMapping("/live")
    public String livenessCheck() {
        // Simple check to verify if the app is running
        return "Application is alive";
    }

    @GetMapping("/ready")
    public String readinessCheck() {
        // Check if the app is ready to serve traffic
        return "Application is ready";
    }
}
