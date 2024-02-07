package dev.cstraka.bungle.Auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dev.cstraka.bungle.User.UserEntity;
import dev.cstraka.bungle.User.UserRepository;
import net.datafaker.Faker;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");

    @Container
    @ServiceConnection(name = "redis")
    public static GenericContainer<?> redis = new GenericContainer<>("redis:7.2.4").withExposedPorts(6379);

    @Autowired
    private UserRepository userRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    private static final Faker faker = new Faker();
    private String username;
    private String password;
    private UserEntity userEntity;

    @BeforeEach
    void addUser() {
        username = faker.internet().username();
        password = faker.internet().password();

        userEntity = new UserEntity(username, password);
        userRepository.save(userEntity);
    }

    @AfterEach
    void cleanup() {
        if (userEntity != null) {
            userRepository.delete(userEntity);
        }
    }

    @Test
    void postgresConnected() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    void redisConnected() {
        assertTrue(redis.isCreated());
        assertTrue(redis.isRunning());
    }

    private String baseUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    private String loginUrl() {
        return baseUrl() + "/login";
    }

    // private String logoutUrl() {
    //     // this might be 
    //     // return "http://localhost:" + port + "/logout";
    //     return baseUrl() + "/logout";
    // }

    @Test
    void testLogin() {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(loginUrl(), request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // @Test
    // void testLogin401() {
    //     Map<String, String> body = new HashMap<>();
    //     body.put("username", username);
    //     body.put("password", "invalid");

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);

    //     HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
    //     ResponseEntity<String> response = restTemplate.postForEntity(loginUrl(), request, String.class);
    //     assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    // }

    // @Test
    // void testLogout() {
    //     Map<String, String> body = new HashMap<>();
    //     body.put("username", username);
    //     body.put("password", password);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);

    //     HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
    //     restTemplate.postForEntity(loginUrl(), request, String.class);

    //     ResponseEntity<String> response = restTemplate.postForEntity(logoutUrl(), request, String.class);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    // }
}
