package com.jsglobe.toys.service.products;

import lombok.Data;

@Data
public class Product {
    private final Long id;
    private final String name;
    private final Double price;
    private final Double oldPrice;
    private final Boolean stock;
    private final String brand;
}
