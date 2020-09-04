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
class ToysApplicationTests {

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
        final ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
