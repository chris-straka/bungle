package dev.cstraka.bungle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * This file is only for ./gradlew bootTestRun
 * It's for developing locally using testcontainers
 * It doesn't run any tests! It's not ./gradlew test
 * It doesn't use my compose.yaml file. It creates one.
 * It runs my main java files, not my test files
 */
@TestConfiguration
public class TestBungleApp {
    // This has nothing to do with ./gradlew test
    // This is just to provide the DB container during development
    @Bean
    @ServiceConnection
    @SuppressWarnings("resource")
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("bungle")
                .withUsername("bungleuser")
                .withPassword("bunglepass")
                .withReuse(true);
    }

    public static void main(String[] args) {
        SpringApplication
                .from(BungleApp::main)
                .with(TestBungleApp.class)
                .run(args);
    }
}
