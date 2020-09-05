package com.jsglobe.toys.service.datasource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CsvFileDataSourceTest {

    public static final String TEST_DATA_FILE_NAME = "TEST_DATA_FILE_NAME";
    public static final String DATA_SOURCE_ERROR_MESSAGE = "DataSource error.";

    @Mock
    private CsvProductParser csvProductParser;

    @InjectMocks
    private CsvFileDataSource csvFileDataSource;

    @BeforeEach
    void startUp() {
        ReflectionTestUtils.setField(csvFileDataSource, "datasourceFileName", TEST_DATA_FILE_NAME);
    }

    @Mock
    private List<DataSourceProduct> dataSourceProductsMocked;

    @Test
    void getAllData_should_return_list_of_products_when_file_does_exist() throws IOException {
        when(csvProductParser.extractFromFile(anyString())).thenReturn(dataSourceProductsMocked);

        final var products = csvFileDataSource.getAllData();
        assertEquals(dataSourceProductsMocked, products);

        verify(csvProductParser, times(1)).extractFromFile(TEST_DATA_FILE_NAME);
    }

    @Test
    void getAllData_should_throw_an_exception_when_it_can_not_read_the_file() throws IOException {
        when(csvProductParser.extractFromFile(anyString())).thenThrow(IOException.class);

        var dataSourceErrorException = assertThrows(
                DataSourceErrorException.class,
                csvFileDataSource::getAllData
        );
        assertEquals(DATA_SOURCE_ERROR_MESSAGE, dataSourceErrorException.getMessage());

        verify(csvProductParser, times(1)).extractFromFile(TEST_DATA_FILE_NAME);
    }
}