package com.jsglobe.toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToysApplicationE2e {

    private static final String ROOT = "/";
    private static final String PRODUCTS_ENDPOINT = "/product";

    @LocalServerPort
    private int localPort;

    private TestRestTemplate restTemplate;

    @BeforeEach
    void startUp() {
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
