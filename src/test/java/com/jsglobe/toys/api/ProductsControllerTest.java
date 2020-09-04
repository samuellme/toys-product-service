package com.jsglobe.toys.api;

import com.jsglobe.toys.api.mapper.ProductVmMapper;
import com.jsglobe.toys.api.model.ProductVM;
import com.jsglobe.toys.service.products.Product;
import com.jsglobe.toys.service.products.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductVmMapper productVmMapper;

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private List<Product> productsMock;

    @Mock
    private List<ProductVM> productVMsMock;

    @Test
    void getAllProduct_should_return_empty_when_product_service_returns_empty() {
        when(productService.getProducts()).thenReturn(emptyList());
        when(productVmMapper.map(any())).thenReturn(emptyList());

        final List<ProductVM> products = productsController.getAllProduct();

        assertThat(products).isEmpty();

        verify(productService, times(1)).getProducts();
        verify(productVmMapper, times(1)).map(emptyList());
    }

    @Test
    void getAllProduct_should_return_mapped_products_from_product_service() {
        when(productService.getProducts()).thenReturn(productsMock);
        when(productVmMapper.map(any())).thenReturn(productVMsMock);

        final List<ProductVM> products = productsController.getAllProduct();

        assertEquals(productVMsMock, products);

        verify(productService, times(1)).getProducts();
        verify(productVmMapper, times(1)).map(productsMock);
    }
}