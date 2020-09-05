package com.jsglobe.toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ToysApplicationE2e {

    private static final String ROOT = "/";
    private static final String PRODUCTS_ENDPOINT = "/product";

    @LocalServerPort
    private int localPort;

    private TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> redisContainer = new GenericContainer<>("redis");
    private static final String REDIS_PORT_BINDING = "6379:6379";

    static {
        redisContainer.setPortBindings(singletonList(REDIS_PORT_BINDING));
    }

    @BeforeEach
    void beforeEach() {
        final RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri(String.format("http://localhost:%d", localPort));
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Test
    void should_return_200_for_root() {
        final ResponseEntity<String> response = restTemplate.getForEntity(ROOT, String.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void should_return_200_for_products_end_point() {
        final ResponseEntity<String> response = restTemplate.getForEntity(PRODUCTS_ENDPOINT, String.class);
        restTemplate.getForEntity(PRODUCTS_ENDPOINT, String.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
