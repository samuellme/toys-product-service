package com.jsglobe.toys.service.datasource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CsvProductParserTest {


    private static final int TOY_A_ID = 43664;
    private static final String TOY_A_NAME = "LEGO #14362905";
    private static final double TOY_A_PRICE = 24.99;
    private static final double TOT_A_OLD_PRICE = 29.99;
    private static final String TOY_A_BRAND = "LEGO";
    private static final long TOY_A_STOCK = 0L;

    private static final int TOY_B_ID = 14541;
    private static final String TOY_B_NAME = "s.Oliver #2119423";
    private static final double TOY_B_PRICE = 25.99;
    private static final double TOT_B_OLD_PRICE = 25.99;
    private static final String TOY_B_BRAND = "s.Oliver";
    private static final long TOY_B_STOCK = 3L;

    @InjectMocks
    private CsvProductParser csvProductParser;

    @Test
    void extractProducts_should_extract_and_map_values_properly() throws IOException {
        final var dataSourceProducts = csvProductParser.extractFromFile(getSampleDataSourceFileName());

        assertEquals(10, dataSourceProducts.size());

        final var sampleItemA = dataSourceProducts.get(0);
        assertEquals(TOY_A_ID, sampleItemA.getId());
        assertEquals(TOY_A_NAME, sampleItemA.getName());
        assertEquals(TOY_A_PRICE, sampleItemA.getPrice());
        assertEquals(TOT_A_OLD_PRICE, sampleItemA.getOldPrice());
        assertEquals(TOY_A_STOCK, sampleItemA.getStock());
        assertEquals(TOY_A_BRAND, sampleItemA.getBrand());

        final var sampleItemB = dataSourceProducts.get(5);
        assertEquals(TOY_B_ID, sampleItemB.getId());
        assertEquals(TOY_B_NAME, sampleItemB.getName());
        assertEquals(TOY_B_PRICE, sampleItemB.getPrice());
        assertEquals(TOT_B_OLD_PRICE, sampleItemB.getOldPrice());
        assertEquals(TOY_B_STOCK, sampleItemB.getStock());
        assertEquals(TOY_B_BRAND, sampleItemB.getBrand());
    }

    @Test
    void extractProducts_should_throw_ioexception_when_file_does_not_exists() {
        assertThrows(IOException.class, () -> csvProductParser.extractFromFile("NOT EXISTED FILE"));
    }

    private String getSampleDataSourceFileName() throws IOException {
        return new ClassPathResource("sample_data.csv").getFile().getAbsolutePath();
    }
}