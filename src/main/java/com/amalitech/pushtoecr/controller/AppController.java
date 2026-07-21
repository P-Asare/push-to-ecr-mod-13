package com.amalitech.pushtoecr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;

@RestController
public class AppController {

    private final Instant startedAt = Instant.now();

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.description}")
    private String appDescription;

    @Value("${app.author}")
    private String appAuthor;

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        long uptimeSeconds = Duration.ofMillis(ManagementFactory.getRuntimeMXBean().getUptime()).toSeconds();
        HealthResponse response = new HealthResponse("UP", Instant.now(), uptimeSeconds);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<InfoResponse> info() {
        InfoResponse response = new InfoResponse(
                appName,
                appVersion,
                appDescription,
                appAuthor,
                System.getProperty("java.version"),
                startedAt
        );
        return ResponseEntity.ok(response);
    }

    public record HealthResponse(String status, Instant timestamp, long uptimeSeconds) {}
    public record InfoResponse(String name, String version, String description, String author, String javaVersion, Instant buildTimestamp) {}
}
