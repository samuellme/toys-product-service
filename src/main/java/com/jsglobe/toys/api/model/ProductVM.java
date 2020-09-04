package com.jsglobe.toys.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductVM {
    private final Long id;
    private final String name;
    private final Double price;

    @JsonProperty("old_price")
    private final Double oldPrice;
    private final Boolean stock;
    private final String brand;
}
