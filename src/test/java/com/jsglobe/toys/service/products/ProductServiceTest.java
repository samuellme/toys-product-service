package com.jsglobe.toys.service.products;

import com.jsglobe.toys.service.datasource.DataSourceProduct;
import com.jsglobe.toys.service.datasource.ProductDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDataSource productDataSource;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Mock
    private List<DataSourceProduct> dataSourceProductsMocked;
    private List<Product> productsMocked;

    @Test
    void getProducts_should_call_and_return_from_data_source() {
        when(productDataSource.getAllData()).thenReturn(dataSourceProductsMocked);
        when(productMapper.map(any())).thenReturn(productsMocked);

        final var products = productService.getProducts();

        assertEquals(productsMocked, products);

        verify(productDataSource, times(1)).getAllData();
        verify(productMapper, times(1)).map(dataSourceProductsMocked);
    }
}