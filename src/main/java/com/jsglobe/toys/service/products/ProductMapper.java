package com.jsglobe.toys.service.products;

import com.jsglobe.toys.service.datasource.DataSourceProduct;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Component
class ProductMapper {

    private static final String THE_ID_IS_REQUIRED = "the Id is required.";
    private static final String THE_NAME_IS_REQUIRED_MESSAGE = "the Name is required.";
    private static final String THE_PRICE_IS_REQUIRED_MESSAGE = "the Price is required.";
    private static final String THE_STOCK_IS_REQUIRED_MESSAGE = "the Stock  is required.";
    private static final String THE_BRAND_IS_REQUIRED_MESSAGE = "the Brand is required.";

    public List<Product> map(List<DataSourceProduct> dataSourceProducts) {
        return dataSourceProducts
                .stream()
                .map(this::map)
                .collect(toList());
    }

    private Product map(DataSourceProduct dataSourceProduct) {
        final Long id = requireNonNull(dataSourceProduct.getId(), THE_ID_IS_REQUIRED);
        final String name = requireNonNull(dataSourceProduct.getName(), THE_NAME_IS_REQUIRED_MESSAGE);
        final Double price = requireNonNull(dataSourceProduct.getPrice(), THE_PRICE_IS_REQUIRED_MESSAGE);
        final Double oldPrice = dataSourceProduct.getOldPrice();
        final Long stock = requireNonNull(dataSourceProduct.getStock(), THE_STOCK_IS_REQUIRED_MESSAGE);
        final String brand = requireNonNull(dataSourceProduct.getBrand(), THE_BRAND_IS_REQUIRED_MESSAGE);

        return new Product(id, name, price, oldPrice, stock, brand);
    }
}
