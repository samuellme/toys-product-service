package com.jsglobe.toys.service.products;

import com.jsglobe.toys.service.datasource.ProductDataSource;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDataSource productDataSource;
    private final ProductMapper productMapper;

    public ProductService(
            ProductDataSource productDataSource,
            ProductMapper productMapper
    ) {
        this.productDataSource = productDataSource;
        this.productMapper = productMapper;
    }

    @NonNull
    public List<Product> getProducts() {
        final var dataSourceProducts = productDataSource.getAllData();
        return productMapper.map(dataSourceProducts);
    }
}
