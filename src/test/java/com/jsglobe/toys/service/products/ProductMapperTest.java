package com.jsglobe.toys.service.products;

import com.jsglobe.toys.service.datasource.DataSourceProduct;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductMapperTest {

    private static final long TOY_ID = 1L;
    private static final String TOY_NAME = "TOY_NAME";
    private static final double TOY_OLD_PRICE = 12.34;
    private static final double TOY_PRICE = 12.05;
    private static final long TOY_STOCK = 14L;
    private static final String TOY_BRAND = "TOY_BRAND";

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void map_should_return_empty_list_when_empty_list_passed() {
        final var mappedProducts = productMapper.map(emptyList());

        assertEquals(emptyList(), mappedProducts);
    }

    @Test
    void map_should_properly_map_the_collection_of_datasource_products() {
        final var dataSourceProduct = getDataSourceProduct();

        final var products = productMapper.map(singletonList(dataSourceProduct));
        assertThat(products).isNotEmpty();

        final var product = products.get(0);
        assertEquals(TOY_ID, product.getId());
        assertEquals(TOY_NAME, product.getName());
        assertEquals(TOY_OLD_PRICE, product.getOldPrice());
        assertEquals(TOY_PRICE, product.getPrice());
        assertEquals(TOY_STOCK, product.getStock());
        assertEquals(TOY_BRAND, product.getBrand());
    }

    @Test
    void map_should_throw_exception_when_null_values_passed() {
        final var dataSourceProduct = new DataSourceProduct();

        final var exception = assertThrows(
                NullPointerException.class,
                () -> productMapper.map(singletonList(dataSourceProduct))
        );

        assertThat(exception.getMessage()).contains("is required");
    }

    private DataSourceProduct getDataSourceProduct() {
        final var dataSourceProduct = new DataSourceProduct();
        dataSourceProduct.setId(TOY_ID);
        dataSourceProduct.setName(TOY_NAME);
        dataSourceProduct.setPrice(TOY_PRICE);
        dataSourceProduct.setOldPrice(TOY_OLD_PRICE);
        dataSourceProduct.setStock(TOY_STOCK);
        dataSourceProduct.setBrand(TOY_BRAND);

        return dataSourceProduct;
    }
}