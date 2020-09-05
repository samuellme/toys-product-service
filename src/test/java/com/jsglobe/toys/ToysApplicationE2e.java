package com.jsglobe.toys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsglobe.toys.api.model.ErrorInfo;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ToysApplicationE2e {

    private static final String ROOT = "/";
    private static final String PRODUCTS_ENDPOINT = "/product";
    public static final String INVALID_ENDPOINT = "/invalid-request";

    @LocalServerPort
    private int localPort;

    private ObjectMapper objectMapper;

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
    void should_return_2xx_Successful_for_root() {
        final ResponseEntity<String> response = restTemplate.getForEntity(ROOT, String.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void should_return_2xx_Successful_for_products_end_point() {
        final ResponseEntity<String> response = restTemplate.getForEntity(PRODUCTS_ENDPOINT, String.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void should_return_properly_loaded_products_from_the_provided_file() {
        final var response = restTemplate.getForObject(
                PRODUCTS_ENDPOINT,
                ResponseProductList.class
        );

        assertNotNull(response);
        assertEquals(10, response.size());

        final var firstItem = response.get(0);
        assertNotNull(firstItem);
        assertEquals(43664, firstItem.id);
        assertEquals("LEGO #14362905", firstItem.name);
        assertEquals(29.99, firstItem.old_price);
        assertEquals(24.99, firstItem.price);
        assertEquals(0, firstItem.stock);
        assertEquals("LEGO", firstItem.brand);

        final var lastItem = response.get(9);
        assertNotNull(firstItem);
        assertEquals(40755, lastItem.id);
        assertEquals("s.Oliver #7538143", lastItem.name);
        assertEquals(8.99, lastItem.old_price);
        assertEquals(8.99, lastItem.price);
        assertEquals(8, lastItem.stock);
        assertEquals("s.Oliver", lastItem.brand);
    }

    @Test
    void should_return_4xx_when_request_is_invalid() {
        final var response = restTemplate.getForEntity(
                INVALID_ENDPOINT,
                ErrorInfo.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    static class ResponseProductList extends ArrayList<ResponseProduct> {
    }

    @Data
    static class ResponseProduct {
        private Long id;
        private String name;
        private Double price;
        private Double old_price;
        private Long stock;
        private String brand;
    }
}
