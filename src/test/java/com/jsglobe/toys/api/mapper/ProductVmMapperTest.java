package com.jsglobe.toys.api.mapper;

import com.jsglobe.toys.api.model.ProductVM;
import com.jsglobe.toys.service.products.Product;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductVmMapperTest {

    public static final boolean PRODUCT_STOCK = false;
    private static final long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_BRAND = "PRODUCT_BRAND";
    public static final double PRODUCT_OLD_PRICE = 12.01;
    public static final double PRODUCT_PRICE = 12.34;

    private final Product product = new Product(
            PRODUCT_ID,
            PRODUCT_NAME,
            PRODUCT_PRICE,
            PRODUCT_OLD_PRICE,
            PRODUCT_STOCK,
            PRODUCT_BRAND
    );

    private final ProductVmMapper productVmMapper = new ProductVmMapper();

    @Test
    void map_should_map_all_fields_properly() {
        final List<ProductVM> products = productVmMapper.map(Collections.singletonList(product));

        assertEquals(1, products.size());

        final ProductVM productVm = products.get(0);
        assertEquals(PRODUCT_ID, productVm.getId());
        assertEquals(PRODUCT_NAME, productVm.getName());
        assertEquals(PRODUCT_OLD_PRICE, productVm.getOldPrice());
        assertEquals(PRODUCT_PRICE, productVm.getPrice());
        assertEquals(PRODUCT_STOCK, productVm.getStock());
        assertEquals(PRODUCT_BRAND, productVm.getBrand());
    }
}